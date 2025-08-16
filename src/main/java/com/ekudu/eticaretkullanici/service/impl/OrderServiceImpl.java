package com.ekudu.eticaretkullanici.service.impl;

import com.ekudu.eticaretkullanici.cache.CartCacheService;
import com.ekudu.eticaretkullanici.client.PaymentClient;
import com.ekudu.eticaretkullanici.dto.*;
import com.ekudu.eticaretkullanici.model.OrderEntity;
import com.ekudu.eticaretkullanici.model.OrderItemEntity;
import com.ekudu.eticaretkullanici.model.ProductEntity;
import com.ekudu.eticaretkullanici.model.UserEntity;
import com.ekudu.eticaretkullanici.repository.OrderItemRepository;
import com.ekudu.eticaretkullanici.repository.OrderRepository;
import com.ekudu.eticaretkullanici.repository.UserRepository;
import com.ekudu.eticaretkullanici.service.OrderService;
import com.ekudu.eticaretkullanici.service.ProductService;
import com.ekudu.eticaretkullanici.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    final UserService userService;
    final CartCacheService cartCacheService;
    final ProductService productService;
    final PaymentClient paymentClient;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;


    @Override
    public OrderResponseDto doPayment(CardDto card) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        Optional<UserEntity> user = userService.getUserByUserName(name);
        if (user.isEmpty()) {
            throw new RuntimeException("Kullanıcı bulunamadı");
        }
        Long userId = user.get().getId();
        Map<Object, Object> cartMap = cartCacheService.getCart(userId);
        double totalPrice = 0;
        for(Object productId : cartMap.keySet()) {
            String quantity = cartMap.get(productId).toString();
            int  quantityInt = Integer.parseInt(quantity);
            ProductResponseDto productResponseDto = productService.getProductById(Long.parseLong(productId.toString()));
            totalPrice += productResponseDto.getPrice() * quantityInt;
        }
        PaymentRequestDto paymentRequestDto = new PaymentRequestDto();
        paymentRequestDto.setAmount(totalPrice);
        paymentRequestDto.setCard(card);
        PaymentResponseDto paymentResponseDto =  paymentClient.processPayment(paymentRequestDto);
        if(!paymentResponseDto.isSuccess()) {
            throw new RuntimeException("Ödeme red oldu");
        }
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setUser(user.get());
        OrderEntity savedOrderEntity = orderRepository.save(orderEntity);


        for(Object productId : cartMap.keySet()) {
            ProductEntity entity = productService.getProdById(Long.parseLong(productId.toString()));
            OrderItemEntity itemEntity = new OrderItemEntity();
            itemEntity.setProductEntity(entity);
            itemEntity.setOrderEntity(orderEntity);
            itemEntity.setCount(Long.parseLong(cartMap.get(productId).toString()));
            orderItemRepository.save(itemEntity);
        }
        // TODO Rabbit Koyulcak


        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setOrderId(savedOrderEntity.getId());
        orderResponseDto.setSuccess(true);
        orderResponseDto.setTotal(totalPrice);




        return orderResponseDto;
    }
}

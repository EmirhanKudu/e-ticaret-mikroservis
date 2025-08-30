package com.ekudu.eticaretkullanici.service.impl;

import com.ekudu.eticaretkullanici.cache.CartCacheService;
import com.ekudu.eticaretkullanici.dto.*;
import com.ekudu.eticaretkullanici.model.UserEntity;
import com.ekudu.eticaretkullanici.service.CartService;
import com.ekudu.eticaretkullanici.service.ProductService;
import com.ekudu.eticaretkullanici.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService {
    final CartCacheService cartCacheService;
    final UserService userService;
    final ProductService productService;


    @Override
    public AddCartResponseDto addCart(AddCartRequestDto addCartRequestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        Optional<UserEntity> user = userService.getUserByUserName(name);
        if(user.isEmpty()){
            throw new RuntimeException("Kullanıcı bulunamadı!");
        }
        Long userId = user.get().getId();
        cartCacheService.addToCart(userId,addCartRequestDto.getProductId().toString(),addCartRequestDto.getQuantity());
        AddCartResponseDto addCartResponseDto = new AddCartResponseDto();
        addCartResponseDto.setSuccess(true);
        addCartResponseDto.setMessage("Ürün sepete başarıyla eklenmiştir!");
        return addCartResponseDto;
    }

    @Override
    public CartAllProductDto getAllCart() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        List<CartProductDto> cartProductDtoList = new ArrayList<>();
        Optional<UserEntity> user = userService.getUserByUserName(name);
        if(user.isEmpty()){
            throw new RuntimeException("Kullanıcı bulunamadı!");
        }
        Long userId = user.get().getId();
        Map<Object,Object> getCartMap = cartCacheService.getCart(userId);
        double totalPrice = 0;
        for(Object productId : getCartMap.keySet()) {
            String quantity = getCartMap.get(productId).toString();
            int quantityInt = Integer.parseInt(quantity);
            ProductResponseDto productResponseDto = productService.getProductById(Long.parseLong(productId.toString()));
            totalPrice += productResponseDto.getPrice() * quantityInt;
            CartProductDto cartProductDto = new CartProductDto();
            cartProductDto.setQuantity(quantityInt);
            cartProductDto.setPrice(productResponseDto.getPrice());
            cartProductDto.setTitle(productResponseDto.getTitle());
            cartProductDtoList.add(cartProductDto);
        }
        CartAllProductDto cartAllProductDto = new CartAllProductDto();
        cartAllProductDto.setTotalPrice(totalPrice);
        cartAllProductDto.setCartProductDtoList(cartProductDtoList);

        return cartAllProductDto;
    }

    @Override
    public String clearAllCart() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        Optional<UserEntity> user = userService.getUserByUserName(name);
        if(user.isEmpty()){
            throw new RuntimeException("Kullanıcı bulunamadı!");
        }
        Long userId = user.get().getId();
        cartCacheService.clearCart(userId);
        return "Sepet boşaltıldı!";
    }





}

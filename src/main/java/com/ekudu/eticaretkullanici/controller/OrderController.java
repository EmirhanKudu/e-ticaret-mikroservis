package com.ekudu.eticaretkullanici.controller;

import com.ekudu.eticaretkullanici.cache.CartCacheService;
import com.ekudu.eticaretkullanici.dto.CardDto;
import com.ekudu.eticaretkullanici.dto.OrderResponseDto;
import com.ekudu.eticaretkullanici.dto.PaymentRequestDto;
import com.ekudu.eticaretkullanici.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/order")
public class OrderController {


    OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/payment")
    public ResponseEntity<OrderResponseDto> payment(@RequestBody CardDto card) {
        return ResponseEntity.ok( orderService.doPayment(card));
    }

}

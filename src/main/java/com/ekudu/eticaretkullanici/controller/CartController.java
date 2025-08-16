package com.ekudu.eticaretkullanici.controller;

import com.ekudu.eticaretkullanici.dto.AddCartRequestDto;
import com.ekudu.eticaretkullanici.dto.AddCartResponseDto;
import com.ekudu.eticaretkullanici.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    final CartService cartService;

    @PostMapping("/add-cart")
    public ResponseEntity<AddCartResponseDto> addToCart(@RequestBody AddCartRequestDto addCartRequestDto) {
        AddCartResponseDto addCartResponseDto = cartService.addCart(addCartRequestDto);

        return ResponseEntity.ok(addCartResponseDto);
    }

}

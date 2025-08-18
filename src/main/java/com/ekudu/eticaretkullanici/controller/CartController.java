package com.ekudu.eticaretkullanici.controller;

import com.ekudu.eticaretkullanici.dto.AddCartRequestDto;
import com.ekudu.eticaretkullanici.dto.AddCartResponseDto;
import com.ekudu.eticaretkullanici.dto.CartAllProductDto;
import com.ekudu.eticaretkullanici.dto.CartProductDto;
import com.ekudu.eticaretkullanici.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/get-cart")
    public ResponseEntity<CartAllProductDto> getCart() {
        CartAllProductDto cartAllProductDto =  cartService.getAllCart();
        return ResponseEntity.ok(cartAllProductDto);
    }

    @GetMapping("/delete-cart")
    public ResponseEntity<String> deleteCart() {
        String message = cartService.clearAllCart();
        return ResponseEntity.ok(message);
    }


}

package com.ekudu.eticaretkullanici.controller;

import com.ekudu.eticaretkullanici.cache.CartCacheService;
import com.ekudu.eticaretkullanici.dto.UserResponseDto;
import com.ekudu.eticaretkullanici.model.UserEntity;
import com.ekudu.eticaretkullanici.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final        CartCacheService   cartCacheService;
    private final UserService userService;

    @GetMapping("/index")
    public String index() {
        return "Index Sayfası , Hoş geldiniz!";
    }
    @GetMapping("/dashboard")
    public String dashboard() {
        return "Login başarılı - dashboard sayfası!";
    }

    @GetMapping("/add-card")
    public String addCard() {
        cartCacheService.addToCart(1L, "URUN", 1);
        return "Add Card";
    }

    @GetMapping("/get-card")
    public String getCard() {
        Map<Object, Object> cart = cartCacheService.getCart(1L);
        return "Get Card";
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<UserResponseDto> userInfo(@PathVariable Long id) {
        UserResponseDto idInfo = userService.getUserById(id);
        return ResponseEntity.ok(idInfo);



    }
}

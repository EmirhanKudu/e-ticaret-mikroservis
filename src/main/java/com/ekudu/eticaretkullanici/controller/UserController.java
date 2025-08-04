package com.ekudu.eticaretkullanici.controller;

import com.ekudu.eticaretkullanici.cache.CartCacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final        CartCacheService   cartCacheService;

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
}

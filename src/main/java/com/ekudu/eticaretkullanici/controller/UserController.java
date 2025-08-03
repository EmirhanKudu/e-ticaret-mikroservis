package com.ekudu.eticaretkullanici.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/index")
    public String index() {
        return "Index Sayfası , Hoş geldiniz!";
    }
    @GetMapping("/dashboard")
    public String dashboard() {
        return "Login başarılı - dashboard sayfası";
    }
}

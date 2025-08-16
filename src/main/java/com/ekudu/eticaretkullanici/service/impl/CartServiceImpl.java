package com.ekudu.eticaretkullanici.service.impl;

import com.ekudu.eticaretkullanici.cache.CartCacheService;
import com.ekudu.eticaretkullanici.dto.AddCartRequestDto;
import com.ekudu.eticaretkullanici.dto.AddCartResponseDto;
import com.ekudu.eticaretkullanici.model.UserEntity;
import com.ekudu.eticaretkullanici.service.CartService;
import com.ekudu.eticaretkullanici.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService {
    final CartCacheService cartCacheService;
    final UserService userService;


    @Override
    public AddCartResponseDto addCart(AddCartRequestDto addCartRequestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        Optional<UserEntity> user = userService.getUserByUserName(name);
        if(user.isEmpty()){
            throw new RuntimeException("Kullanıcı bulunamadı");
        }
        Long userId = user.get().getId();
        cartCacheService.addToCart(userId,addCartRequestDto.getProductId().toString(),addCartRequestDto.getQuantity());
        AddCartResponseDto addCartResponseDto = new AddCartResponseDto();
        addCartResponseDto.setSuccess(true);
        addCartResponseDto.setMessage("Ürün sepete başarıyla eklenmiştir!");
        return addCartResponseDto;
    }





}

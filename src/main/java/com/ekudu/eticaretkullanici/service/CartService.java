package com.ekudu.eticaretkullanici.service;

import com.ekudu.eticaretkullanici.dto.AddCartRequestDto;
import com.ekudu.eticaretkullanici.dto.AddCartResponseDto;
import com.ekudu.eticaretkullanici.dto.CartAllProductDto;

public interface CartService {

    AddCartResponseDto addCart (AddCartRequestDto addCartRequestDto);



    CartAllProductDto getAllCart();



    String clearAllCart();
}

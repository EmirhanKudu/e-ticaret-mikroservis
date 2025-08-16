package com.ekudu.eticaretkullanici.service;

import com.ekudu.eticaretkullanici.dto.AddCartRequestDto;
import com.ekudu.eticaretkullanici.dto.AddCartResponseDto;

public interface CartService {

    AddCartResponseDto addCart (AddCartRequestDto addCartRequestDto);

}

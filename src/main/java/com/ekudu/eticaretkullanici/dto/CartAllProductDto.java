package com.ekudu.eticaretkullanici.dto;

import lombok.Data;

import java.util.List;

@Data
public class CartAllProductDto {
    private List<CartProductDto> cartProductDtoList;
    private double totalPrice;

}

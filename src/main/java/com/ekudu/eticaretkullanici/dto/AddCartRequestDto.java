package com.ekudu.eticaretkullanici.dto;

import lombok.Data;

@Data
public class AddCartRequestDto {

    private Long productId;
    private int quantity;
}

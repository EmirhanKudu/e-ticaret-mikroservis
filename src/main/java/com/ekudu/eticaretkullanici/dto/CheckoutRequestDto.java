package com.ekudu.eticaretkullanici.dto;

import lombok.Data;

@Data
public class CheckoutRequestDto {
    private double totalPrice;
    private CardDto card;
}

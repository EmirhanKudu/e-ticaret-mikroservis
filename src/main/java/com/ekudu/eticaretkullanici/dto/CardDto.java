package com.ekudu.eticaretkullanici.dto;

import lombok.Data;

@Data
public class CardDto {
    private String cardNumber;
    private String cardHolderName;
    private String cardExpiration;
    private String cardCvv;
}

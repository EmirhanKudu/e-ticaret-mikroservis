package com.ekudu.eticaretkullanici.dto;

import lombok.Data;

@Data
public class PaymentRequestDto {
    private double amount;
    private CardDto card;

}

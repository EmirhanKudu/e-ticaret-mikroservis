package com.ekudu.eticaretkullanici.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data

public class OrderResponseDto {
    private Long OrderId;
    private boolean isSuccess;
    private String orderFailMessage;
    private double total;
}

package com.ekudu.eticaretkullanici.dto;

import lombok.Data;

@Data
public class OrderItemDto {
    private Long productId;
    private String productName;
    private Long quantity;
}

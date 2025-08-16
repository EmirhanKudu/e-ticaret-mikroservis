package com.ekudu.eticaretkullanici.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class ProductOrderId {
    private Long productId;
    private Long orderId;
}

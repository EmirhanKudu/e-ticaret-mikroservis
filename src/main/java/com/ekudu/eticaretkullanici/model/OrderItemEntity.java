package com.ekudu.eticaretkullanici.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "siparis_urun")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class OrderItemEntity {

    /*
    @EmbeddedId
    private ProductOrderId prodOrderId;*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;

    private Long count;


}

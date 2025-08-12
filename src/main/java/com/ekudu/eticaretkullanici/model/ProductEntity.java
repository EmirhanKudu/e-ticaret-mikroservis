package com.ekudu.eticaretkullanici.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "urun")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private String description;

    private Integer stock;

    private String photo;






}

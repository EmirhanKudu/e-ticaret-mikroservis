package com.ekudu.eticaretkullanici.dto;

import lombok.Builder;
import lombok.Data;
@Data
public class ProductResponseDto {
     private Long id;
     private String title;
     private String description;
     private double price;
     private Integer stock;




}

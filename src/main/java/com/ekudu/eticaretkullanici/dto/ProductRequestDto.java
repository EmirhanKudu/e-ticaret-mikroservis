package com.ekudu.eticaretkullanici.dto;
import jakarta.validation.constraints.*;


import lombok.Data;



@Data
public class ProductRequestDto {
    @NotBlank(message = "Ürün başlığı boş olamaz!")
    private String title;

    private String description;

    @NotNull(message = "Fiyat boş olamaz!")
    @DecimalMin("0.0")
    private double price;

    @Min(0)
    private Integer stock;
}

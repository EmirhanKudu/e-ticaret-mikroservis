package com.ekudu.eticaretkullanici.service;

import com.ekudu.eticaretkullanici.dto.ProductRequestDto;
import com.ekudu.eticaretkullanici.dto.ProductResponseDto;

import java.util.List;

public interface ProductService {

    ProductResponseDto createProduct(ProductRequestDto dto);
    ProductResponseDto updateProduct(ProductRequestDto dto);
    ProductResponseDto partialUpdateProduct(ProductRequestDto dto);
    ProductResponseDto getProductById(long id);
    List<ProductResponseDto> listAll();
    void deleteProduct(long id);

}

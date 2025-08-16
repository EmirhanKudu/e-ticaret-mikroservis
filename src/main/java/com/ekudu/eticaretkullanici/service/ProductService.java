package com.ekudu.eticaretkullanici.service;

import com.ekudu.eticaretkullanici.dto.ProductRequestDto;
import com.ekudu.eticaretkullanici.dto.ProductResponseDto;
import com.ekudu.eticaretkullanici.model.ProductEntity;
import jakarta.transaction.Transactional;

import java.util.List;

public interface ProductService {

    ProductResponseDto createProduct(ProductRequestDto dto);

    @Transactional
    ProductResponseDto updateProduct(Long id, ProductRequestDto dto);

    ProductResponseDto getProductById(Long id);

    ProductEntity getProdById(Long id);

    List<ProductResponseDto> listAll();

    void deleteProduct(Long id);
}

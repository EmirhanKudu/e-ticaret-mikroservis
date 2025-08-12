package com.ekudu.eticaretkullanici.service.impl;


import com.ekudu.eticaretkullanici.dto.ProductRequestDto;
import com.ekudu.eticaretkullanici.dto.ProductResponseDto;
import com.ekudu.eticaretkullanici.model.ProductEntity;
import com.ekudu.eticaretkullanici.repository.ProductRepository;
import com.ekudu.eticaretkullanici.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repo;
    public ProductServiceImpl(ProductRepository repo) {
        this.repo = repo;
    }
    @Override
    @Transactional
    public ProductResponseDto createProduct(ProductRequestDto dto) {
    ProductEntity entity = ProductEntity.builder()
            .title(dto.getTitle())
            .price(dto.getPrice())
            .description(dto.getDescription())
            .stock(dto.getStock()).build();
    ProductEntity saved = repo.save(entity);
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(saved.getId());
        productResponseDto.setTitle(saved.getTitle());
        productResponseDto.setPrice(saved.getPrice());
        productResponseDto.setDescription(saved.getDescription());
        productResponseDto.setStock(saved.getStock());

        return productResponseDto;
    }

    @Override
    public ProductResponseDto updateProduct(ProductRequestDto dto) {
        return null;
    }

    @Override
    public ProductResponseDto partialUpdateProduct(ProductRequestDto dto) {
        return null;
    }

    @Override
    public ProductResponseDto getProductById(long id) {
        return null;
    }

    @Override
    public List<ProductResponseDto> listAll() {
        return List.of();
    }

    @Override
    public void deleteProduct(long id) {

    }

}
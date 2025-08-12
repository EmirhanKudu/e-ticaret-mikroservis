package com.ekudu.eticaretkullanici.service.impl;


import com.ekudu.eticaretkullanici.dto.ProductRequestDto;
import com.ekudu.eticaretkullanici.dto.ProductResponseDto;
import com.ekudu.eticaretkullanici.model.ProductEntity;
import com.ekudu.eticaretkullanici.repository.ProductRepository;
import com.ekudu.eticaretkullanici.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Transactional
    @Override
    public ProductResponseDto updateProduct(Long id, ProductRequestDto dto) {
        ProductEntity existing = repo.findById(id).orElseThrow(()-> new RuntimeException("Ürün bulunamadı!"));
        existing.setTitle(dto.getTitle());
        existing.setPrice(dto.getPrice());
        existing.setDescription(dto.getDescription());
        existing.setStock(dto.getStock());
        ProductEntity saved = repo.save(existing);
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setTitle(saved.getTitle());
        productResponseDto.setPrice(saved.getPrice());
        productResponseDto.setDescription(saved.getDescription());
        productResponseDto.setStock(saved.getStock());
        return productResponseDto;
    }



    @Override
    public List<ProductResponseDto> listAll() {
        List<ProductEntity> products = repo.findAll();
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();

        for (ProductEntity entity  : products) {
            ProductResponseDto dto = new ProductResponseDto();
            dto.setId(entity.getId());
            dto.setTitle(entity.getTitle());
            dto.setPrice(entity.getPrice());
            dto.setDescription(entity.getDescription());
            dto.setStock(entity.getStock());
            productResponseDtos.add(dto);
        }
        return productResponseDtos;
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        if (repo.existsById(id)) {
            throw new RuntimeException("Ürün bulunamadı!");
        }
        repo.deleteById(id);

    }

    @Override
    public ProductResponseDto getProductById(Long id) {
        ProductEntity entity = repo.findById(id).orElseThrow(() -> new RuntimeException("Ürün bulunamadı!"));
        ProductResponseDto productResponseDto = new  ProductResponseDto();
        productResponseDto.setId(entity.getId());
        productResponseDto.setTitle(entity.getTitle());
        productResponseDto.setPrice(entity.getPrice());
        productResponseDto.setDescription(entity.getDescription());
        productResponseDto.setStock(entity.getStock());
        return productResponseDto;

    }





}








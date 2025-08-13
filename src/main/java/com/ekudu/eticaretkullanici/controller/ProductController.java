package com.ekudu.eticaretkullanici.controller;


import com.ekudu.eticaretkullanici.dto.ProductRequestDto;
import com.ekudu.eticaretkullanici.dto.ProductResponseDto;
import com.ekudu.eticaretkullanici.service.ProductService;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
@Validated
public class ProductController {

    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public ResponseEntity<ProductResponseDto> create(@Valid @RequestBody ProductRequestDto dto) {
        ProductResponseDto created = productService.createProduct(dto);
        return ResponseEntity.ok(created);
    }
    @GetMapping("/product-info")
    public ResponseEntity<ProductResponseDto> getProductInfo(@RequestParam("id") Long id) {
        ProductResponseDto info = productService.getProductById(id);
        return ResponseEntity.ok(info);
    }
    @GetMapping("/get-all")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        List<ProductResponseDto> getAllProducts = productService.listAll();
        return ResponseEntity.ok(getAllProducts);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<ProductResponseDto> deleteProduct(@RequestParam("id") Long id) {
        ProductResponseDto deletedProduct = productService.getProductById(id);
        productService.deleteProduct(id);
        return ResponseEntity.ok(deletedProduct);

    }
    @PutMapping("/update")
    public ResponseEntity<ProductResponseDto> updateProduct(@RequestParam Long id, @Valid @RequestBody ProductRequestDto dto) {

        ProductResponseDto updated = productService.updateProduct(id, dto);
        return ResponseEntity.ok(updated);
    }







}

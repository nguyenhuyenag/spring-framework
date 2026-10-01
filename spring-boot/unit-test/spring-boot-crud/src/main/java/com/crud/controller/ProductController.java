package com.crud.controller;

import com.crud.dto.ApiResponse;
import com.crud.entity.Product;
import com.crud.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping("/addProduct")
    public ResponseEntity<ApiResponse<Product>> addProduct(@RequestBody Product request) {
        log.info("Starting /addProduct endpoint");
        Product product = service.saveProduct(request);
        ApiResponse<Product> response = ApiResponse.<Product>builder()
                .error_code(0)
                .message("success")
                .result(product)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/addProducts")
    public ResponseEntity<?> addProducts(@RequestBody List<Product> products) {
        log.info("Products added successfully");
        return ResponseEntity.ok(service.saveProducts(products));
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> findAllProducts() {
        log.info("Fetching all products");
        return ResponseEntity.ok(service.getProducts());
    }

    @GetMapping("/productById/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable int id) {
        log.info("Fetching product by ID: {}", id);
        return ResponseEntity.ok(service.getProductById(id));
    }

    @GetMapping("/product/{name}")
    public ResponseEntity<Product> findProductByName(@PathVariable String name) {
        log.info("Fetching product by name: {}", name);
        return ResponseEntity.ok(service.getProductByName(name));
    }

    @PutMapping("/update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        log.info("Updating product: {}", product.getId());
        return ResponseEntity.ok(service.updateProduct(product));
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        log.info("Deleting product by ID: {}", id);
        return service.deleteProduct(id);
    }

}

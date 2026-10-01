package com.crud.service;

import com.crud.entity.Product;
import com.crud.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Product saveProduct(Product product) {
        log.info("Starting saveProduct method");
        return repository.save(product);
    }

    public List<Product> saveProducts(List<Product> products) {
        log.info("Starting saveProducts method");
        return repository.saveAll(products);
    }

    public List<Product> getProducts() {
        log.info("Starting getProducts method");
        return repository.findAll();
    }

    public Product getProductById(int id) {
        log.info("Starting getProductById method");
        return repository.findById(id).orElse(null);
    }

    public Product getProductByName(String name) {
        log.info("Starting getProductByName method");
        return repository.findByName(name);
    }

    public String deleteProduct(int id) {
        log.info("Starting deleteProduct method");
        repository.deleteById(id);
        return "product removed !! " + id;
    }

    public Product updateProduct(Product product) {
        log.info("Starting updateProduct method");
        Product existingProduct = repository.findById(product.getId()).orElse(null);
        existingProduct.setName(product.getName());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setPrice(product.getPrice());
        log.info("Product updated successfully: {}", existingProduct.getId());
        return repository.save(existingProduct);
    }

}

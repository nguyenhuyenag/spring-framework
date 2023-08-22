package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.manytomany.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}

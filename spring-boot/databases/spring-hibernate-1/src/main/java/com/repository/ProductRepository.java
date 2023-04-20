package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.manytomany.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}

package com.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	@Query(value = "SELECT t FROM Product t")
	Page<Product> findAllWithTypeId(Pageable pageable);
	
}

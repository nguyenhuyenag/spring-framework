package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.TProduct;

public interface TProductRepository extends JpaRepository<TProduct, Long> {
	
}

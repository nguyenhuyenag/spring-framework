package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.createupdatetime.Customer;

// @Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	Optional<Customer> findByEmail(String email);
	
}

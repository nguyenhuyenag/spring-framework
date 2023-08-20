package com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.createupdatetime.Customer;

// @Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	Optional<Customer> findByEmail(String email);
	
	Customer findFirstByName(String name);
	
	Customer findFirstByNameLike(String name); // String name = "xyz%";
	
	Customer findFirstByNameOrderById(String name);
	
	Customer findFirstByNameOrderByIdDesc(String name);
	
	List<Customer> findFirst2ByNameOrderByIdDesc(String name);
	
	List<Customer> findTop2ByNameOrderByIdDesc(String name);
	
}

package com.primary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.primary.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}

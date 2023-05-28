package com.multitenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multitenant.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}

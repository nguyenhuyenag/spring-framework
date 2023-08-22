package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.createupdatetime.Customer;

@Repository
public interface TransientRepository extends JpaRepository<Customer, Integer> {

}

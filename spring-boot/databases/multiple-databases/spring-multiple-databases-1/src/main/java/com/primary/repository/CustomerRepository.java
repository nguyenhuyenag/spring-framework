package com.primary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.primary.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

//	@Transactional //("transaction1Manager")
//	@Modifying
//	@Query(value = "DELETE FROM customer WHERE id > 114", nativeQuery = true)
//	public Integer delete();

}

package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.BankAccount;

// Repository for test -> Change JpaRepository<XXX, Integer>
public interface TestRepository extends JpaRepository<BankAccount, Integer> {

}

package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.BankAccount;

public interface BankRepository extends JpaRepository<BankAccount, Integer> {

}

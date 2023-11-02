package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.onetoone.withJoinColumn.AddressJC;

public interface AddressJCRepository extends JpaRepository<AddressJC, Integer> {

}

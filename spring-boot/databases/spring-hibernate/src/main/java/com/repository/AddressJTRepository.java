package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.onetoone.withJoinTable.AddressJT;

public interface AddressJTRepository extends JpaRepository<AddressJT, Integer> {

}

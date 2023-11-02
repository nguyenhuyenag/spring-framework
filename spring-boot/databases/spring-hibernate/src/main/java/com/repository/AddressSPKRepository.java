package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.onetoone.withSharedPrimaryKey.AddressSPK;

public interface AddressSPKRepository extends JpaRepository<AddressSPK, Integer> {

}

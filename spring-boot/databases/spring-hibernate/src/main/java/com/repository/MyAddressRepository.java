package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.onetoone.withJoinColumn.MyUser;

public interface MyAddressRepository extends JpaRepository<MyUser, Integer> {

}

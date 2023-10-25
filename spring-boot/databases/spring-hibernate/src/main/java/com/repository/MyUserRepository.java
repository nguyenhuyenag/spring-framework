package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.onetoone.withJoinColumn.MyUser;

public interface MyUserRepository extends JpaRepository<MyUser, Integer> {

}

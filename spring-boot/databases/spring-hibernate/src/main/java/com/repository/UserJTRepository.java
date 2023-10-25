package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.onetoone.withJoinTable.UserJT;

public interface UserJTRepository extends JpaRepository<UserJT, Integer> {

}

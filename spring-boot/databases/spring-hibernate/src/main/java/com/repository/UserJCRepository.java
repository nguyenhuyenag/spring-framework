package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.onetoone.withJoinColumn.UserJC;

public interface UserJCRepository extends JpaRepository<UserJC, Integer> {

}

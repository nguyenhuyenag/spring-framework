package com.second.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.second.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}

package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.entity.User;

@Repository
public interface QLTKRepository extends JpaRepository<User, Integer> {
	
	// @Query("select u from User u where u.email = :email")
	User findByEmail(@Param("email") String email);
	
}

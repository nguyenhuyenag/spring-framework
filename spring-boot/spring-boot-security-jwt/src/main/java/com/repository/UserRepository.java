package com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);
	
	//@Query(value = "select u from User u where u.id = :id", nativeQuery = true)
	//Optional<User> findById(Long id);
	
	// Optional<User> findByUsernameOrEmail(String username, String email);

	// boolean existsByUsernameOrEmail(String username, String email);

	// Native SQL
	@Query(value = "select * from User", nativeQuery = true)
	List<User> getAllUser();

	// @Query("select u from User u where u.username = :usernameOrEmail or u.email =
	// :usernameOrEmail")
	// public Optional<User> findByUsernameOrEmail(@Param("usernameOrEmail") String
	// usernameOrEmail);

}

package com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	// Query creation from method names

	// long countByLastname(String lastname);

	// User findFirstByOrderByLastnameAsc();

	// long deleteByLastname(String lastname);

	// User findByFirstname(String firstname);

	User findUserByName(String lastname);

	// List<User> findAllByLastname(String lastname);

	// List<User> findDistinctUserByFirstnameOrLastname(String firstname, String
	// lastname);

	// List<User> findByFirstnameIgnoreCase(String firstname);

	// List<User> findByFirstnameAllIgnoreCase(String firstname);

	// List<User> findAllByOrderByFirstnameAsc(); // don't miss "by"

	// List<User> findAllByOrderByEmailAddressDesc();
	
	@Query("SELECT DISTINCT u.name FROM User u")
	List<String> findDistinctUserByName();
	
	@Query("select u.email from User u")
	List<String> getAllEmails();

	// JPQL
	@Query(value = "SELECT u FROM User u ORDER BY email")
	List<User> JPQL();

	// JPQL
	@Query(value = "SELECT m FROM User m WHERE m.name LIKE :input%")
	List<User> startWith(@Param("input") String input);

	// Native SQL
	@Query(value = "SELECT * FROM t_user u WHERE u.id = :id", nativeQuery = true)
	Optional<User> findByIdNativeSQL(@Param("id") int id);

}

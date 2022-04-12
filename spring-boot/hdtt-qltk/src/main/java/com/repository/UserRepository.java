package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	Optional<User> findByEmail(String email);

	@Query(value = "SELECT t.* FROM users t WHERE t.username = :username ", nativeQuery = true)
	public User getUser(@Param("username") String username);

	@Modifying
	@Transactional
	@Query(value = "UPDATE users t SET t.password = :password WHERE t.username = :username ", nativeQuery = true)
	public void changePassword(@Param("username") String username, @Param("password") String password);

}

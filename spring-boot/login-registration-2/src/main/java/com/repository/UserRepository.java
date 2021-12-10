package com.repository;

import java.util.Date;
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

	Optional<User> findByUsername(String email);

	@Modifying
	@Transactional
	@Query(value = "UPDATE user t SET t.failed_attempt = 0 WHERE t.username = :username", nativeQuery = true)
	void resetFailedAttempt(@Param("username") String username);

	@Modifying
	@Transactional
	@Query(value = "UPDATE user t SET t.account_locked = 1, t.lock_attempt_time = :locktime WHERE t.username = :username", nativeQuery = true)
	void lockAttempt(@Param("username") String username, @Param("locktime") Date locktime);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE user t SET t.account_locked = 0 WHERE t.username = :username", nativeQuery = true)
	void unlock(@Param("username") String username);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE user t SET t.failed_attempt = (t.failed_attempt + 1) WHERE t.username = :username", nativeQuery = true)
	void increaseFailedAttempt(@Param("username") String username);

}

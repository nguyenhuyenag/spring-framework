package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByUsername(String username);

	/**
	 * Hàm gọi phương thức này cần được đánh dấu @Transactional
	 */
	@Modifying
	@Query("UPDATE User t SET t.failedAttempt = :failedAttempt WHERE t.username = :username")
	public void updateFailedAttempt(String username, int failedAttempt);

}

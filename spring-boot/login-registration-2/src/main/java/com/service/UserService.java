package com.service;

import java.util.Optional;

import com.entity.User;

public interface UserService {
	
	Optional<User> findByUsername(String username);

	void lock(String username);
	
	void unlock(String username);

	void resetFailedAttempt(String username);

	void increaseFailedAttempt(String username);

}

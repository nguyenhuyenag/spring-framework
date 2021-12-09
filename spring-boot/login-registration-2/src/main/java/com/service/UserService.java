package com.service;

import com.entity.User;

public interface UserService {

	User findByUsername(String username);

	void lock(String username);

	void unlock(String username);

	void resetFailedAttempt(String username);

	void increaseFailedAttempt(String username);

}

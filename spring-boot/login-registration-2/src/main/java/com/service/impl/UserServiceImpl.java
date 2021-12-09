package com.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.User;
import com.repository.UserRepository;
import com.service.UserService;
import com.util.DateTimeUtils;

@Service
public class UserServiceImpl implements UserService {

	private long LOCK_TIME = 5 * DateTimeUtils.ONE_MINUTE;

	@Autowired
	private UserRepository repository;

	@Override
	public User findByUsername(String username) {
		Optional<User> opt = repository.findByUsername(username);
		return opt.orElse(null);
	}

	@Override
	public void increaseFailedAttempt(String username) {
		repository.increaseFailedAttempt(username);
	}

	@Override
	public void resetFailedAttempt(String username) {
		repository.resetFailedAttempt(username);
	}

	@Override
	public void lock(String username) {
		repository.lock(username, DateTimeUtils.getLaterDate(LOCK_TIME));
	}

	@Override
	public void unlock(String username) {
		repository.unlock(username);
	}

}

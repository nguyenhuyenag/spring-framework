package com.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.User;
import com.repository.UserRepository;
import com.service.UserService;
import com.util.DateTimeUtils;

@Service
public class UserServiceImpl implements UserService {

	private final long LOCK_ATTEMPT_TIME = 30 * DateTimeUtils.ONE_SECOND;

	@Autowired
	private UserRepository repository;

	@Override
	public User findByUsername(String username) {
		Optional<User> opt = repository.findByUsername(username);
		return opt.orElse(null);
	}

	@Override
	public void lockAttempt(String username) {
		Date date = DateTimeUtils.getLaterDate(LOCK_ATTEMPT_TIME);
		repository.lockAttempt(username, date);
	}

	@Override
	public void unlock(String username) {
		repository.unlock(username);
	}

}

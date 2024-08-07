package com.service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entity.User;
import com.repository.UserRepository;

@Service
@Transactional
public class LoginAttemptService {

	public static final int MAX_FAILED_ATTEMPTS = 5;

	private static final long LOCK_TIME_DURATION = TimeUnit.MINUTES.toMillis(1);

	@Autowired
	private UserRepository userRepository;

	public void increaseFailedAttempts(User user) {
		int newFailAttempt = user.getFailedAttempt() + 1;
		userRepository.updateFailedAttempt(user.getUsername(), newFailAttempt);
	}

	public void resetFailedAttempts(String username) {
		userRepository.updateFailedAttempt(username, 0);
	}

	public void lock(User user) {
		user.setAccountNonLocked(false);
		user.setLockTime(new Date());
		userRepository.save(user);
	}

	public boolean unlockWhenTimeExpired(User user) {
		Date lockTime = user.getLockTime();
		if (lockTime != null) {
			long lockTimeInMillis = lockTime.getTime();
			long currentTimeInMillis = System.currentTimeMillis();
			if (lockTimeInMillis + LOCK_TIME_DURATION < currentTimeInMillis) {
				user.setLockTime(null);
				user.setFailedAttempt(0);
				user.setAccountNonLocked(true);
				userRepository.save(user);
				return true;
			}
		}
		return false;
	}

}

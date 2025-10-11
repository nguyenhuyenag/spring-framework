package com.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class LoginAttemptService {

	private final int MAX_ATTEMPT = 3;
	private static Map<String, Integer> attemptsCache = new HashMap<>();

	public void loginFailed(String username) {
		Integer v = attemptsCache.get(username);
		if (v != null) {
			attemptsCache.put(username, ++v);
		} else {
			attemptsCache.put(username, 1);
		}
	}

	public void resetAttemptsCache(String username) {
		attemptsCache.putIfAbsent(username, 0);
	}

	public boolean isBlocked(String key) {
		Integer v = attemptsCache.get(key);
		return (v != null && v >= MAX_ATTEMPT);
	}

}

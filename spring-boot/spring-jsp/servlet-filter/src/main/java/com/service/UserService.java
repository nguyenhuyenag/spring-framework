package com.service;

import java.util.HashMap;
import java.util.Map;

import com.config.SecurityConfig;
import com.model.User;

public class UserService {

	private static final Map<String, User> map = new HashMap<>();

	static {
		init();
	}

	private static void init() {
		// User này có 1 vai trò là EMPLOYEE
		User user1 = new User("employee1", "123", SecurityConfig.ROLE_EMPLOYEE);
		// User này có 2 vai trò EMPLOYEE và MANAGER
		User user2 = new User("manager1", "123", SecurityConfig.ROLE_EMPLOYEE, SecurityConfig.ROLE_MANAGER);
		map.put(user1.getUsername(), user1);
		map.put(user2.getUsername(), user2);
	}

	// Tìm kiếm người dùng theo username và password
	public static User findUser(String username, String password) {
		User user = map.get(username);
		if (user != null && user.getPassword().equals(password)) {
			user.setPassword("********");
			return user;
		}
		return null;
	}

}

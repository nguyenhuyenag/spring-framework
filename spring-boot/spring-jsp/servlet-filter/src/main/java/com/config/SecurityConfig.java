package com.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SecurityConfig {

	public static final String ROLE_EMPLOYEE = "ROLE_EMPLOYEE";
	public static final String ROLE_MANAGER  = "ROLE_MANAGER";

	private static final Set<String> set = new HashSet<>();
	private static final Map<String, List<String>> config = new HashMap<>();

	static {
		init();
	}

	private static void init() {
		set.addAll(Arrays.asList("/userInfo", "/employee", "/manager"));
		// Cấu hình cho vai trò "EMPLOYEE"
		config.put(ROLE_EMPLOYEE, Arrays.asList("/userInfo", "/employee"));
		// Cấu hình cho vai trò "MANAGER"
		config.put(ROLE_MANAGER, Arrays.asList("/userInfo", "/manager"));
	}

	public static Set<String> getAllAppRoles() {
		return config.keySet();
	}

	public static List<String> getUrlPatternsForRole(String role) {
		return config.get(role);
	}

	public static Set<String> listProtectedPage() {
		return set;
	}

}

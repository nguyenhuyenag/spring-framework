package com.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.http.HttpSession;

import com.model.User;

public class WebUtils {

	private static final String LOGINED_USER = "loginedUser";

	private static final Map<String, String> storeRedirectUrl = new HashMap<>();

	// Lưu trữ thông tin người dùng vào Session
	public static void storeLoginedUser(HttpSession session, User user) {
		session.setAttribute(LOGINED_USER, user);
	}

	// Lấy thông tin người dùng lưu trữ trong Session
	public static User getUserFromSession(HttpSession session) {
		return (User) session.getAttribute(LOGINED_USER);
	}

	public static String storeRedirectUrlAfterLogin(String requestUri) {
		String key = random();
		storeRedirectUrl.put(key, requestUri);
		return key;
	}

	public static String getRedirectAfterLoginUrl(String redirectId) {
		String requestUri = storeRedirectUrl.get(redirectId);
		storeRedirectUrl.remove(redirectId);
		return requestUri;
	}

	private static String random() {
		while (true) {
			int n = ThreadLocalRandom.current().nextInt();
			if (n > 0) {
				return String.valueOf(n);
			}
		}
	}
}

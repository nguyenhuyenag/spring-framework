package com.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface TokenAuthService {

	/**
	 * Add Authentication
	 * @param response
	 * @param authentication
	 */
	void addAuthentication(HttpServletResponse response, Authentication authentication);

	/**
	 * Authentication after login
	 * @param request
	 * @param userService
	 * @return
	 */
	Authentication getAuthentication(HttpServletRequest request, UserDetailsService userService);
}

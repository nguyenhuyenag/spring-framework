package com.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface TokenAuthenticationService {

	void addAuthentication(HttpServletResponse res, Authentication authentication);

	Authentication getAuthentication(HttpServletRequest request, UserDetailsService userService);
}

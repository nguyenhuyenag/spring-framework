package com.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

// @Service
public class CustomLogoutHandler extends SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		String username = getAuthenticatedUserName(authentication);
		System.out.println("[" + this.getClass().getSimpleName() + "] " + "The user `" + username + "` has logged out.");
		super.onLogoutSuccess(request, response, authentication);
	}

	public static String getAuthenticatedUserName(Authentication auth) {
		return auth != null ? ((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername()
				: null;
	}

}

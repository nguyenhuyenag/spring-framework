package com.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class CustomLogoutHandler extends SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, //
			Authentication authentication) throws IOException, ServletException {
		String username = getAuthenticatedUserName(authentication);
		log.info("The user `{}` has logged out.", username);
		super.onLogoutSuccess(request, response, authentication);
	}

	public static String getAuthenticatedUserName(Authentication auth) {
		return auth != null
				? ((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername()
				: null;
	}

}

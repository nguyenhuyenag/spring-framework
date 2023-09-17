package com.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.service.LoginAttemptService;

@Component
public class CustomLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Autowired
	private LoginAttemptService loginAttemptService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		// ShopmeUserDetails userDetails = (ShopmeUserDetails)
		// User user = userDetails.getUser();

		// Need ???
		// if (user.getFailedAttempt() > 0) {
		// }

		String username = authentication.getName();
		loginAttemptService.resetFailedAttempts(username);

		super.onAuthenticationSuccess(request, response, authentication);
	}

}

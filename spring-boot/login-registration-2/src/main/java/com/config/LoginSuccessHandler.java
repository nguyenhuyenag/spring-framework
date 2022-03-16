package com.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	// @Autowired
	// private UserService userService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// String username = request.getParameter("username");
		// String password = request.getParameter("password");
		// System.out.println("[LoginSuccessHandler]: Username: " + username);
		// System.out.println("[LoginSuccessHandler]: PWD: " + password);
		// userService.resetFailedAttempt(username);
		// userService.unlock(username);
		// super.setDefaultTargetUrl("");
		super.onAuthenticationSuccess(request, response, authentication);
	}

}

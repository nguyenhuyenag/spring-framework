package com.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.entity.User;
import com.service.UserService;

@Component
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	private static final int MAX_FAILED_ATTEMPTS = 5;

	// @Autowired
	// private UserRepository userService;
	
	@Autowired
	private UserService userService;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("[LoginFailureHandler]: Username: " + username);
		System.out.println("[LoginFailureHandler]: PWD: " + password);
		User user = userService.findByUsername(username);
		if (user == null) {
			exception = new UsernameNotFoundException("[LoginFailureHandler]: Account `" + username + "` was not found!");
		}
		userService.increaseFailedAttempt(username);
		if (user.getStatus() == 0) {
			exception = new DisabledException("[LoginFailureHandler]: Your account has been disabled!");
		}
//		if (user.getFailedAttempt() >= MAX_FAILED_ATTEMPTS) {
//			userService.lock(username);
//            exception = new LockedException("Your account has been locked due to " + MAX_FAILED_ATTEMPTS +  "failed attempts."
//                    + " It will be unlocked after 5 minutes.");
//		}
		super.setDefaultFailureUrl("/login?error=true");
		super.onAuthenticationFailure(request, response, exception);
	}

}

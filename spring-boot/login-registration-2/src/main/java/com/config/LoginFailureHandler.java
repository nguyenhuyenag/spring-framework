package com.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.entity.User;
import com.service.UserService;
import com.util.DateTimeUtils;

@Component
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	private static final int MAX_FAILED_ATTEMPTS = 5;

	@Autowired
	private UserService userService;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		String username = request.getParameter("username");
		User user = userService.findByUsername(username);
		if (user == null) {
			exception = new UsernameNotFoundException("[LoginFailureHandler]: Tài khoản `" + username + "` không tồn tại!");
		} else {
			userService.increaseFailedAttempt(username);
			if (user.getDisabled() != 0) {
				exception = new DisabledException("[LoginFailureHandler]: Tài khoản chưa kích hoạt!");
			}
			if (DateTimeUtils.lockAttempt(user.getLockAttemptTime())) {
				exception = new LockedException("Tài khoản đang tạm thời bị khóa!");
			}
//			if (user.getFailedAttempt() >= MAX_FAILED_ATTEMPTS && DateTimeUtils.lockAttempt(user.getLockAttemptTime())) {
//				userService.lockAttempt(username);
//				exception = new LockedException("Tài khoản đang tạm thời bị khóa!");
//			}
		}
		super.setDefaultFailureUrl("/login?error=true");
		super.onAuthenticationFailure(request, response, exception);
	}

}

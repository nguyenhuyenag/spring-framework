package com.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.entity.User;
import com.service.LoginAttemptService;
import com.service.UserService;

@Component
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	// private static final int MAX_FAILED_ATTEMPTS = 5;

	@Autowired
	private UserService userService;

	@Autowired
	private LoginAttemptService loginAttemptService;

	// @Autowired
	// private MessageSource messages;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		String username = request.getParameter("username");
		User user = userService.findByUsername(username);
		if (user == null) {
			exception = new UsernameNotFoundException(
					"[LoginFailureHandler]: Tài khoản `" + username + "` không tồn tại!");
		} else {
			exception = handleError(user, username);
//			loginAttemptService.loginFailed(username);
//			if (loginAttemptService.isBlocked(username)) {
//				userService.lockAttempt(username);
//				loginAttemptService.resetAttemptsCache(username);
//			}
//			if (user.getDisabled() != 0) {
//				exception = new DisabledException("[LoginFailureHandler]: Tài khoản chưa kích hoạt!");
//			}
//			if (DateTimeUtils.checkLockAttempt(user.getLockAttemptTime())) {
//				exception = new LockedException("[LoginFailureHandler]: Tài khoản đang tạm thời bị khóa!");
//			}
//			String errorMessage = messages.getMessage("message.badCredentials");
//	        if (exception.getMessage().equalsIgnoreCase("blocked")) {
//	            errorMessage = messages.getMessage("auth.message.blocked", null, locale);
//	        }
//			if (user.getFailedAttempt() >= MAX_FAILED_ATTEMPTS && DateTimeUtils.lockAttempt(user.getLockAttemptTime())) {
//				userService.lockAttempt(username);
//				exception = new LockedException("Tài khoản đang tạm thời bị khóa!");
//			}
		}
		super.setDefaultFailureUrl("/login?error=true");
		super.onAuthenticationFailure(request, response, exception);
	}

	private AuthenticationException handleError(User user, String username) {
		loginAttemptService.loginFailed(username);
		if (loginAttemptService.isBlocked(username)) {
			userService.lockAttempt(username);
			loginAttemptService.resetAttemptsCache(username);
			return new DisabledException("[LoginFailureHandler]: Login quá 5 lần, tài khoản bị khóa tạm thời");
		}
		if (user.getDisabled() != 0) {
			return new DisabledException("[LoginFailureHandler]: Tài khoản chưa kích hoạt!");
		}
		return new BadCredentialsException("[LoginFailureHandler]: Tài khoản chưa kích hoạt!");
	}

}

package com.auth;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.entity.User;
import com.repository.UserRepository;
import com.service.LoginAttemptService;

public class CustomLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	@Autowired
	private UserRepository repository;

	@Autowired
	private LoginAttemptService loginAttemptService;

	/**
	 * Giá trị của `exception` sẽ được hiển thị trên view
	 */
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		String message = "";

		System.out.println("[" + this.getClass().getSimpleName() + "] Exception: " + exception.getMessage());

		String username = request.getParameter("username");
		if (StringUtils.isEmpty(username)) {
			exception = new UsernameNotFoundException("Username must be provided");
		} else {
			Optional<User> userOpt = repository.findByUsername(username);
			if (userOpt.isEmpty()) {
				message = "User `" + username + "` was not found!";
				exception = new UsernameNotFoundException(message);
			} else {
				User user = userOpt.get();
				if (user.isEnabled() && user.isAccountNonLocked()) {
					if (user.getFailedAttempt() < LoginAttemptService.MAX_FAILED_ATTEMPTS - 1) {
						loginAttemptService.increaseFailedAttempts(user);
					} else {
						loginAttemptService.lock(user);
						message = "Your account has been locked due to " //
								+ LoginAttemptService.MAX_FAILED_ATTEMPTS + " failed attempts.";
						exception = new LockedException(message);
					}
				} else if (!user.isAccountNonLocked()) {
					if (loginAttemptService.unlockWhenTimeExpired(user)) {
						message = "Your account has been unlocked. Please try to login again.";
						exception = new LockedException(message);
					}
				}
			}
		}

		if (StringUtils.isNotEmpty(message)) {
			writeResponce(response, message);
		}

		/**
		 * Cần phải có 2 bước này ở cuối
		 */
		super.setDefaultFailureUrl("/login?error=true");
		super.onAuthenticationFailure(request, response, exception);
	}

	private void writeResponce(HttpServletResponse response, String message) throws IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		Map<String, String> errors = new HashMap<>();
		errors.put("error", message);
		
		out.write(errors.toString());
		// out.flush();
	}

}

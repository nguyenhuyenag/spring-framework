package com.auth;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.entity.User;
import com.repository.UserRepository;
import com.service.LoginAttemptService;

public class CustomLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	private final Logger LOG = LoggerFactory.getLogger(CustomLoginFailureHandler.class);

	@Autowired
	private UserRepository repository;

	@Autowired
	private LoginAttemptService loginAttemptService;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		System.out.println("[" + this.getClass().getSimpleName() + "] Exception: " + exception.getMessage());

		String username = request.getParameter("username");
		if (StringUtils.isEmpty(username)) {
			exception = new UsernameNotFoundException("Username must be provided");
		} else {
			Optional<User> opt = repository.findByUsername(username);
			if (opt.isEmpty()) {
				exception = new UsernameNotFoundException("User `" + username + "` was not found!");
			} else {
				User user = opt.get();
				if (user.isEnabled() && user.isAccountNonLocked()) {
					if (user.getFailedAttempt() < LoginAttemptService.MAX_FAILED_ATTEMPTS - 1) {
						loginAttemptService.increaseFailedAttempts(user);
					} else {
						loginAttemptService.lock(user);
						exception = new LockedException("Your account has been locked due to " //
								+ LoginAttemptService.MAX_FAILED_ATTEMPTS + " failed attempts."
								+ " It will be unlocked after 24 hours.");
					}
				} else if (!user.isAccountNonLocked()) {
					if (loginAttemptService.unlockWhenTimeExpired(user)) {
						exception = new LockedException("Your account has been unlocked. Please try to login again.");
					}
				}
			}
		}
		
		super.setDefaultFailureUrl("/login?error=true");
		super.onAuthenticationFailure(request, response, exception);
	}

	// Set exception
	private AuthenticationException handleError(User user) {
		if (user == null) {
			return new UsernameNotFoundException("Username_Not_Found_Exception");
		}
//		if (user.getEnabled() == 0) {
//			return new DisabledException("Disabled_Exception");
//		}
		return new BadCredentialsException("Bad_Credentials_Exception");
	}

}

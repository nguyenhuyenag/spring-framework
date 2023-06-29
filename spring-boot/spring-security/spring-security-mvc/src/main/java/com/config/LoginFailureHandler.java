package com.config;

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
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.entity.User;
import com.repository.UserRepository;
import com.util.LoginAttemptService;
import com.util.RequestUtils;

@Component
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	private static final Logger LOG = LoggerFactory.getLogger(LoginFailureHandler.class);

	@Autowired
	private UserRepository repository;

	@Autowired
	private LoginAttemptService loginAttemptService;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		String email = request.getParameter("username");
		if (StringUtils.isNotEmpty(email)) {
			Optional<User> opt = repository.findByUsername(email);
			if (opt.isPresent()) {
				exception = handleError(opt.get());
			}
		}
		// Login failed by BadCredentialsException (username or password incorrect)
		if (exception.getClass().isAssignableFrom(BadCredentialsException.class)) {
			LOG.info("IP: {}", RequestUtils.getClientIPAddress(request));
			loginAttemptService.loginFailed(RequestUtils.getClientIPAddress(request));
		}
		super.setDefaultFailureUrl("/login?error=true");
		super.onAuthenticationFailure(request, response, exception);
	}

	private AuthenticationException handleError(User user) {
		if (user == null) {
			return new UsernameNotFoundException("Username_Not_Found_Exception");
		}
		if (user.getEnabled() == 0) {
			return new DisabledException("Disabled_Exception");
		}
		return new BadCredentialsException("Bad_Credentials_Exception");
	}

}

package com.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.entity.User;
import com.service.UserService;

@Component
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	@Autowired
	private UserService userService;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		String email = request.getParameter("email");
		if (StringUtils.isNotEmpty(email)) {
			User user = userService.findByEmail(email);
			exception = handleError(user);
		}
		super.setDefaultFailureUrl("/login?error=true");
		super.onAuthenticationFailure(request, response, exception);
	}

	private AuthenticationException handleError(User user) {
		if (user == null) {
			return new UsernameNotFoundException("Username_Not_Found_Exception");
		}
		if (user.getActive() == 0) {
			return new DisabledException("Disabled_Exception");
		}
		return new BadCredentialsException("Bad_Credentials_Exception");
	}

}

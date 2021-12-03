package com.config;

import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

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
import com.repository.UserRepository;

@Component
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	@Autowired
	private UserRepository repository;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		Principal principal = request.getUserPrincipal();
        String username = principal.getName();
		final Optional<User> opt = repository.findByUsername(username);
		if (opt.isPresent()) {
			User user = opt.get();
			if (user.getStatus() == 0) {
				exception = new DisabledException("Your account has been disabled!");
			}
		} else {
			exception = new UsernameNotFoundException("Account `" + username + "` was not found!");
		}
		super.setDefaultFailureUrl("/login?error=true");
		super.onAuthenticationFailure(request, response, exception);
	}

}

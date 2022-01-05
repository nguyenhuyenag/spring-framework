package com.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.entity.User;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.request.LoginRequest;
import com.response.LoginResponse;
import com.service.UserService;
import com.util.JsonUtils;

// @Component
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {
	
	private final int MAX_ATTEMPT = 5;
	
	@Autowired
	private UserService userService;
	
	// @Autowired
	// UserRepository userRepository;

	private LoginRequest login = new LoginRequest();

	public JWTLoginFilter(AuthenticationManager am, UserService userService) {
		super(new AntPathRequestMatcher("/auth/login"));
		this.setAuthenticationManager(am);
		this.userService = userService;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws JsonParseException, JsonMappingException, IOException {
		UsernamePasswordAuthenticationToken auth = null;
		try {
			this.login = JsonUtils.readValue(req.getInputStream(), LoginRequest.class);
			if (login != null) {
				auth = new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword());
			}
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}
		SecurityContextHolder.getContext().setAuthentication(auth);
		return getAuthenticationManager().authenticate(auth);
	}

	// login successful
	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		String token = TokenHandler.generateToken(auth);
		String json = JsonUtils.toJSON(new LoginResponse(token));
		res.getWriter().write(json);
		// res.addHeader(HttpHeaders.AUTHORIZATION, TokenHandler.PREFIX + token);
		res.addHeader(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8");
	}

	// login fail
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest req, HttpServletResponse res,
			AuthenticationException failed) throws IOException, ServletException {
		whenLoginFailure();
		res.getWriter().write("Authentication failed, reason: " + failed.getMessage());
	}

	public void whenLoginFailure() {
		if (this.login != null) {
			// System.out.println(this.login.getUsername());
			// System.out.println(userService == null);
			// System.out.println(userRepository == null);
			User user = userService.findByUsername(this.login.getUsername());
			if (user != null && !user.isLoginDisabled()) {
				int failedCounter = user.getFailedCounter();
				if (MAX_ATTEMPT < failedCounter + 1) {
					// disabling the account
					user.setLoginDisabled(1);
					// user.setFailedCounter(0); // reset counter
				} else {
					// let's update the counter
					user.setFailedCounter(failedCounter + 1);
				}
				userService.save(user);
			}
		}
	}

}

package com.filter;

import java.io.IOException;
import java.util.StringJoiner;

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

import com.entity.RefreshToken;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.payload.reponse.ErrorResponse;
import com.payload.reponse.JwtResponse;
import com.payload.request.LoginRequest;
import com.service.RefreshTokenService;
import com.util.JsonUtils;
import com.util.TokenHandler;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

	// @Autowired
	// private UserService userService;

	@Autowired
	private RefreshTokenService refreshTokenService;

	// private final int MAX_ATTEMPT = 5;
	private LoginRequest login = new LoginRequest();

	public JWTLoginFilter(AuthenticationManager am, RefreshTokenService refreshTokenService) {
		super(new AntPathRequestMatcher("/auth/login"));
		// this.userService = service;
		this.refreshTokenService = refreshTokenService;
		this.setAuthenticationManager(am);
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

	// Login successful
	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		String username = auth.getName();
		String authorities = getAuthorities(auth);
		String jwt = TokenHandler.createJWT(username, authorities);
		RefreshToken createRefreshToken = refreshTokenService.createRefreshToken(username);
		String refreshToken = createRefreshToken.getToken();
		String json = JsonUtils.toJSON(new JwtResponse(jwt, refreshToken));
		res.getWriter().write(json);
		res.addHeader(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8");
		// res.addHeader(HttpHeaders.AUTHORIZATION, TokenHandler.PREFIX + token);
	}

	// Login fail
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest req, HttpServletResponse res,
			AuthenticationException failed) throws IOException, ServletException {
		// whenLoginFailure();
		res.setStatus(401);
		ErrorResponse error = new ErrorResponse();
		error.setStatus(401);
		error.setError("Unsuccessful authenticationabababababaab");
		error.setMessage("From JWTLoginFilter.unsuccessfulAuthentication()");
		error.setPath(req.getRequestURI());
		String json = JsonUtils.toJSON(error);
		res.getWriter().write(json);
	}

	private String getAuthorities(Authentication auth) {
		StringJoiner authorities = new StringJoiner(",");
		auth.getAuthorities().stream().forEach(t -> {
			authorities.add(t.getAuthority());
		});
		return authorities.toString();
	}

//	public void whenLoginFailure() {
//		if (this.login != null) {
//			User user = userService.findByUsername(this.login.getUsername());
//			if (user != null && !user.isLoginDisabled()) {
//				int failedCounter = user.getFailedCounter();
//				if (MAX_ATTEMPT < failedCounter + 1) {
//					user.setFailedCounter(0); // reset counter
//					user.setLoginDisabled(1); // disabling the account
//					user.setTimeLoginDisabled(TimeUtils.after().minute(5));
//				} else {
//					user.setFailedCounter(failedCounter + 1); // update the counter
//				}
//				userService.save(user);
//			}
//		}
//	}

}

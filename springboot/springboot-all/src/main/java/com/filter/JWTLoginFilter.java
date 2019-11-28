package com.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.entity.User;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.request.LoginRequest;
import com.response.ApiError;
import com.response.LoginResponse;
import com.util.JsonUtils;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

	// private ThreadLocal<Integer> rememberMe = new ThreadLocal<>();

	public JWTLoginFilter(AuthenticationManager auth) {
		super(new AntPathRequestMatcher("/auth/login"));
		this.setAuthenticationManager(auth);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws JsonParseException, JsonMappingException, IOException {
		try {
			LoginRequest login = JsonUtils.readValue(req.getInputStream(), LoginRequest.class);
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword(), new ArrayList<>());
			return getAuthenticationManager().authenticate(auth);
		} catch (AuthenticationException e) {
			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			res.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
			ApiError error = new ApiError(401, "Unauthorized", "The username or password is incorrect", req.getRequestURI());
			String json = JsonUtils.writeAsString(error);
			res.getWriter().write(json);
		}
		return null;
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException, ServletException {
		User user = (User) auth.getPrincipal();
		String username = user.getUsername();
		String json = JsonUtils.writeAsString(new LoginResponse(user.getRole(), username));
		res.getWriter().write(json);
		String role = "";
		Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
		for (GrantedAuthority authority : authorities) {
			role = authority.getAuthority();
		}
		String token = TokenHandler.buildToken(username, role);
		res.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
		res.addHeader(HttpHeaders.AUTHORIZATION, TokenHandler.PREFIX + token);
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest req, HttpServletResponse res, AuthenticationException e) throws IOException, ServletException {
		res.getWriter().write("Authentication failed, reason: " + e.getMessage());
	}
}

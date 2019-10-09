package com.boot.filter;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.util.UrlPathHelper;

import com.boot.entity.User;
import com.boot.request.LoginRequest;
import com.boot.response.Res;
import com.boot.response.UserResponse;
import com.boot.service.TokenAuthService;
import com.boot.service.impl.TokenAuthServiceImpl;
import com.boot.util.JSONUtils;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(JWTLoginFilter.class);

	private static final UrlPathHelper URL = new UrlPathHelper();

	private static TokenAuthService tokenAuthService = new TokenAuthServiceImpl();

	public JWTLoginFilter(String url, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
		LoginRequest login = JSONUtils.OBJECT_MAPPER.readValue(request.getInputStream(), LoginRequest.class);
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword(), Collections.emptyList());
		return getAuthenticationManager().authenticate(auth); 
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth) throws IOException, ServletException {
		User user = (User) auth.getPrincipal();
		UserResponse object = new UserResponse(user.getUsername(), user.getRole());
		String json = JSONUtils.objectToJSON(object);
		response.getWriter().write(json);
		response.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
		tokenAuthService.addAuthentication(response, auth);
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		LOGGER.info("Failed authentication while attempting to access " + URL.getPathWithinApplication(request));
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType("application/json");
		Res res = new Res(HttpServletResponse.SC_UNAUTHORIZED, "Username or password is incorrect!");
		String json = JSONUtils.objectToJSON(res);
		response.getWriter().write(json);
		response.getWriter().flush();
	}

}


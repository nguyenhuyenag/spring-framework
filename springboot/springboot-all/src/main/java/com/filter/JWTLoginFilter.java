package com.filter;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.entity.User;
import com.exception.HandlerException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.request.LoginRequest;
import com.response.UserResponse;
import com.util.JsonUtils;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

	// private static final Logger LOGGER =
	// LoggerFactory.getLogger(JWTLoginFilter.class);
	// private static final UrlPathHelper URL = new UrlPathHelper();

	public JWTLoginFilter(String url, AuthenticationManager auth) {
		super(new AntPathRequestMatcher(url));
		this.setAuthenticationManager(auth);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws JsonParseException, JsonMappingException, IOException {
		// LoginRequest login = JsonUtils.MAPPER.readValue(request.getInputStream(),
		// LoginRequest.class);
		// UsernamePasswordAuthenticationToken auth = new
		// UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword(),
		// new ArrayList<>());
		// return this.getAuthenticationManager().authenticate(auth);
		try {
			LoginRequest login = JsonUtils.MAPPER.readValue(request.getInputStream(), LoginRequest.class);
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword(), new ArrayList<>());
			return this.getAuthenticationManager().authenticate(auth);
		} catch (AuthenticationException e) {
			throw new HandlerException("The user name or password is incorrect", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		User user = (User) auth.getPrincipal();
		UserResponse object = new UserResponse(user.getUsername(), user.getRole());
		String json = JsonUtils.writeAsString(object);
		response.getWriter().write(json);
		response.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
		String token = TokenHandler.buildToken(user.getUsername());
		response.addHeader(HttpHeaders.AUTHORIZATION, TokenHandler.PREFIX + token);
	}
}

package com.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.reponse.ErrorResponse;
import com.reponse.LoginResponse;
import com.request.LoginRequest;
import com.util.JsonUtils;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {
	
	public JWTLoginFilter(AuthenticationManager am) {
		super(new AntPathRequestMatcher("/auth/login"));
		this.setAuthenticationManager(am);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws JsonParseException, JsonMappingException, IOException {
		UsernamePasswordAuthenticationToken auth = null;
		try {
			LoginRequest login = JsonUtils.readValue(req.getInputStream(), LoginRequest.class);
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
		res.setStatus(401);
		res.setContentType("application/json;charset=UTF-8");
		
		ErrorResponse error = new ErrorResponse();
		error.setStatus(401);
		error.setError("Unsuccessful authentication");
		error.setMessage("From JWTLoginFilter.unsuccessfulAuthentication()");
		error.setPath(req.getRequestURI());
		
		String json = JsonUtils.toJSON(error);
		res.getWriter().write(json);
	}

}

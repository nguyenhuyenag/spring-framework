package com.filter;

import java.io.IOException;
import java.util.ArrayList;

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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.request.LoginRequest;
import com.response.LoginResponse;
import com.util.JsonUtils;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

	// private static final Logger LOG = LoggerFactory.getLogger(JWTLoginFilter.class);

	public JWTLoginFilter(AuthenticationManager am) {
		super(new AntPathRequestMatcher("/auth/login"));
		this.setAuthenticationManager(am);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws JsonParseException, JsonMappingException, IOException {
		//try {
			LoginRequest login = JsonUtils.readValue(req.getInputStream(), LoginRequest.class);
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword(), new ArrayList<>());
			SecurityContextHolder.getContext().setAuthentication(auth);
			return getAuthenticationManager().authenticate(auth);
//		} catch (AuthenticationException e) {
//			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//			res.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
//			ApiError error = new ApiError();
//			error.setStatus(401);
//			error.setError("Unauthorized");
//			error.setMessage("The username or password is incorrect");
//			error.setPath(req.getRequestURI());
//			String json = JsonUtils.toJSON(error);
//			res.getWriter().write(json);
//		}
//		return null;
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		String token = TokenHandler.generateToken(auth);
		String json = JsonUtils.toJSON(new LoginResponse(token));
		res.getWriter().write(json);
		res.addHeader(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8");
		// res.addHeader(HttpHeaders.AUTHORIZATION, TokenHandler.PREFIX + token);
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest req, HttpServletResponse res,
			AuthenticationException e) throws IOException, ServletException {
		res.getWriter().write("Authentication failed, reason: " + e.getMessage());
	}
}

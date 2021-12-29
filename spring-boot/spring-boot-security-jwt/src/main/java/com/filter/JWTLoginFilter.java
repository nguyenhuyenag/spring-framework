package com.filter;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
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
import com.reponse.LoginAttempt;
import com.request.LoginRequest;
import com.response.LoginResponse;
import com.util.DateTimeUtils;
import com.util.JsonUtils;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

	private final int MAX_ATTEMPT = 5;
	private LoginRequest login = new LoginRequest();
	public static Map<String, LoginAttempt> countAttempt = new ConcurrentHashMap<>();

	public JWTLoginFilter(AuthenticationManager am) {
		super(new AntPathRequestMatcher("/auth/login"));
		this.setAuthenticationManager(am);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws JsonParseException, JsonMappingException, IOException {
		UsernamePasswordAuthenticationToken auth = null;
		try {
			login = JsonUtils.readValue(req.getInputStream(), LoginRequest.class);
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
		res.getWriter().write("Authentication failed, reason: " + failed.getMessage());
		String username = login.getUsername();
		if (StringUtils.isNotEmpty(username)) {
			LoginAttempt attempt = countAttempt.get(username);
			if (attempt == null) {
				countAttempt.put(username, new LoginAttempt(1, null));
			} else {
				Date lockTime = attempt.getLockTime();
				if (lockTime != null) {
					if (lockTime.before(new Date())) {
						System.out.println("Tai khoan tam thoi bi khoa");
					} else {
						int count = attempt.getCount();
						if (count >= MAX_ATTEMPT) {
							lockTime = DateTimeUtils.getLaterDate(5 * DateTimeUtils.ONE_MINUTE);
							countAttempt.put(username, new LoginAttempt(0, lockTime));
						} else {
							countAttempt.put(username, new LoginAttempt(attempt.getCount() + 1, attempt.getLockTime()));
						}
					}
				} else {
					countAttempt.put(username, new LoginAttempt(attempt.getCount() + 1, attempt.getLockTime()));
				}
			}
			System.out.println(countAttempt.get(username));
		}
	}

}

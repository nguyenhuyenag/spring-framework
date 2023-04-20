package com.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.dto.LoginDto;
import com.dto.UserClient;
import com.entity.primary.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.TokenAuthenticationService;
import com.service.impl.TokenAuthenticationServiceImpl;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {
	
	private TokenAuthenticationService tokenAuthenticationService = new TokenAuthenticationServiceImpl();
	
	public JWTLoginFilter(String url, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException, IOException, ServletException {
		LoginDto account = new ObjectMapper().readValue(req.getInputStream(), LoginDto.class);
		return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(account.getUsername(), account.getPassword(), Collections.emptyList()));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException, ServletException {
		ObjectMapper mapper = new ObjectMapper();
		User principal = (User) auth.getPrincipal();
		UserClient user = new UserClient();
		user.setId(principal.getId());
		user.setUsername(principal.getUsername());
		user.setFullName(principal.getFullName());
		user.setEmail(principal.getEmail());
		user.setRole(principal.getRole());
		String json = mapper.writeValueAsString(user);
		PrintWriter out = res.getWriter();
		out.write(json);
		res.addHeader("Content-Type", "application/json; charset=UTF-8");
		tokenAuthenticationService.addAuthentication(res, auth);
	}

}

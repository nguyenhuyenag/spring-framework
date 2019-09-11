package com.spring.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.GenericFilterBean;

import com.spring.service.TokenAuthenticationService;
import com.spring.service.impl.TokenAuthenticationServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;

public class JWTAuthenticationFilter extends GenericFilterBean {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	private TokenAuthenticationService tokenAuthenticationService = new TokenAuthenticationServiceImpl();
	
	public JWTAuthenticationFilter(UserDetailsService userService) {
		this.userDetailsService = userService;
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		try {
			Authentication authentication = tokenAuthenticationService.getAuthentication((HttpServletRequest) request, userDetailsService);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			filterChain.doFilter(request, response);
		} catch (ExpiredJwtException e) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token expire");
			return;
		}
	}
}

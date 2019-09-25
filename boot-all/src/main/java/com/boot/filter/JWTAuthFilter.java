package com.boot.filter;

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

import com.boot.service.TokenAuthService;
import com.boot.service.impl.TokenAuthServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;

public class JWTAuthFilter extends GenericFilterBean {

	@Autowired
	private UserDetailsService userDetailsService;

	public JWTAuthFilter(UserDetailsService userService) {
		this.userDetailsService = userService;
	}

	private TokenAuthService tokenAuthService = new TokenAuthServiceImpl();

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) //
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		try {
			Authentication auth = tokenAuthService.getAuthentication(httpRequest, userDetailsService);
			SecurityContextHolder.getContext().setAuthentication(auth);
			chain.doFilter(httpRequest, httpResponse);
		} catch (ExpiredJwtException e) {
			e.printStackTrace();
			httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, TokenHandler.TOKEN_EXPIRES);
			return;
		}
	}
}

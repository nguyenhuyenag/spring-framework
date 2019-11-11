package com.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.exception.HandlerException;

/**
 * We should use OncePerRequestFilter since we are doing a database call, there
 * is no point in doing this more than once
 */
public class JwtFilter extends OncePerRequestFilter {

	private JwtProvider jwtTokenProvider;

	public JwtFilter(JwtProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) //
			throws ServletException, IOException {
		String token = jwtTokenProvider.resolveToken(request);
		try {
			if (StringUtils.isNotEmpty(token) && jwtTokenProvider.validateToken(token)) {
				Authentication auth = jwtTokenProvider.getAuthentication(token);
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		} catch (HandlerException e) {
			// This is very important
			// Since it guarantees the user is not authenticated at all
			SecurityContextHolder.clearContext();
			response.sendError(e.getHttpStatus().value(), e.getMessage());
			return;
		}
		filterChain.doFilter(request, response);
	}

}

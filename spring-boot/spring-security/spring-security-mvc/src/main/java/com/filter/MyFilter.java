package com.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE) // important
public class MyFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, //
			FilterChain filterChain) throws ServletException, IOException {
		String uri = request.getRequestURI();
		StringBuffer url = request.getRequestURL();
		System.out.println("[" + this.getClass().getSimpleName() + "] RequestURL: " + url);
		System.out.println("[" + this.getClass().getSimpleName() + "] RequestURI: " + uri);
		filterChain.doFilter(request, response);
	}

}

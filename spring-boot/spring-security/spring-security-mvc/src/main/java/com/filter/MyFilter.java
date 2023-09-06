package com.filter;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE) // important
public class MyFilter extends OncePerRequestFilter {

	String[] excludedPrefixes = { "/static", "/j_spring_security_check" };

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, //
			FilterChain filterChain) throws ServletException, IOException {
		// StringBuffer url = request.getRequestURL();
		// System.out.println("[" + this.getClass().getSimpleName() + "] URL: " + url);
		String uri = request.getRequestURI();
		System.out.println("[" + this.getClass().getSimpleName() + "] URI: " + uri);
		filterChain.doFilter(request, response);
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		String uri = StringUtils.defaultString(request.getRequestURI());
		String contextPath = request.getContextPath();
		// Bỏ qua những request này
		return Arrays.stream(excludedPrefixes) //
				.map(s -> contextPath + s) //
				.anyMatch(item -> uri.startsWith(item));
	}

}

package com.filter;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE) // important
public class MyFilter extends OncePerRequestFilter {

	@Autowired
	protected CsrfTokenRepository csrfTokenRepository;

	@Autowired
	protected CsrfToken csrfToken;

	String[] excludedPrefixes = { "/static", "/j_spring_security_check" };

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, //
			FilterChain filterChain) throws ServletException, IOException {
		// showRequestURL(request);
		extractCsrfToken(request);
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

	protected String getClientIP(HttpServletRequest request) {
		String xfHeader = request.getHeader("X-Forwarded-For");
		if (xfHeader == null || xfHeader.isEmpty() || !xfHeader.contains(request.getRemoteAddr())) {
			return request.getRemoteAddr();
		}
		return xfHeader.split(",")[0];
	}

	protected void showRequestURL(HttpServletRequest request) {
		StringBuffer url = request.getRequestURL();
		System.out.println("[" + this.getClass().getSimpleName() + "] URL: " + url);
		String uri = request.getRequestURI();
		System.out.println("[" + this.getClass().getSimpleName() + "] URI: " + uri);
		System.out.println("ClientIP: " + getClientIP(request));
	}

	protected void extractCsrfToken(HttpServletRequest request) {
		// HttpSessionCsrfTokenRepository csrfTokenRepository = new HttpSessionCsrfTokenRepository();
		CsrfToken csrfToken = csrfTokenRepository.loadToken(request);
		if (csrfToken != null) {
			String token = csrfToken.getToken();
			String token2 = csrfToken.getToken();
			System.out.println("Request Csrf Token: " + token);
			System.out.println("Server Csrf Token: " + token2);
		}
	}

}

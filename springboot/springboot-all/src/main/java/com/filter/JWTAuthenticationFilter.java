package com.filter;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.GenericFilterBean;

import com.entity.User;

public class JWTAuthenticationFilter extends GenericFilterBean {

	private static final Logger LOG = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

	@Autowired
	private UserDetailsService userDetailsService;

	public JWTAuthenticationFilter(final UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	public UsernamePasswordAuthenticationToken getAuthentication(String token) {
		String username = TokenHandler.getUsername(token);
		if (StringUtils.isEmpty(username)) {
			return null;
		}
		User user = (User) userDetailsService.loadUserByUsername(username);
		if (user != null) {
			return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
		}
		return null;
	}

	/**
	 * Xác thực bằng api bằng JWT
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String token = req.getHeader(HttpHeaders.AUTHORIZATION);
		if (StringUtils.isEmpty(token) || !token.startsWith(TokenHandler.PREFIX)) {
			LOG.info("Couldn't find Bearer string");
			chain.doFilter(req, res);
			return;
		}
		UsernamePasswordAuthenticationToken auth = getAuthentication(token);
		SecurityContextHolder.getContext().setAuthentication(auth);
		chain.doFilter(req, res);
	}

}

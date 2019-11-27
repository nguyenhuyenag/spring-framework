package com.filter;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.exception.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

	private static final Logger LOG = LoggerFactory.getLogger(JWTAuthenticationFilter.class);
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}


//	public UsernamePasswordAuthenticationToken getAuthentication(String token) {
//		String username = TokenHandler.getUsername(token);
//		if (StringUtils.isEmpty(username)) {
//			return null;
//		}
//		User user = (User) userDetailsService.loadUserByUsername(username);
//		if (user != null) {
//			return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
//		}
//		return null;
//	}
	
	private UsernamePasswordAuthenticationToken getAuthentication(String tokenHeader) throws TokenExpiredException {
		String token = tokenHeader.replace(TokenHandler.PREFIX, "");
		boolean expiration = TokenHandler.isExpiration(token);
		if (expiration) {
			throw new TokenExpiredException("Token expiration");
		} else {
			String username = TokenHandler.getUsername(token);
			String role = TokenHandler.getUserRole(token);
			if (username != null) {
				return new UsernamePasswordAuthenticationToken(username, null,
						Collections.singleton(new SimpleGrantedAuthority(role)));
			}
		}
		return null;
	}

	/**
	 * Xác thực bằng api bằng JWT
	 */
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//		HttpServletRequest req = (HttpServletRequest) request;
//		HttpServletResponse res = (HttpServletResponse) response;
//		String token = req.getHeader(HttpHeaders.AUTHORIZATION);
//		// String tokenHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
//		if (StringUtils.isEmpty(token) || !token.startsWith(TokenHandler.PREFIX)) {
//			LOG.info("Couldn't find Bearer string");
//			chain.doFilter(req, res);
//			return;
//		}
//		try {
//			UsernamePasswordAuthenticationToken auth = getAuthentication(token);
//			SecurityContextHolder.getContext().setAuthentication(auth);
//			chain.doFilter(req, res);
//		} catch (TokenExpiredException e) {
//		}
//	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String token = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (StringUtils.isEmpty(token) || !token.startsWith(TokenHandler.PREFIX)) {
			LOG.info("Couldn't find Bearer string");
			chain.doFilter(request, response);
			return;
		}
		try {
			SecurityContextHolder.getContext().setAuthentication(getAuthentication(token));
		} catch (TokenExpiredException e) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset=utf-8");
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			String reason = "Error：" + e.getMessage();
			response.getWriter().write(new ObjectMapper().writeValueAsString(reason));
			response.getWriter().flush();
			return;
		}
		super.doFilterInternal(request, response, chain);
	}

}

package com.spring.service.impl;

import static java.util.Collections.emptyList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.spring.entity.primary.User;
import com.spring.filter.TokenHandler;
import com.spring.service.TokenAuthenticationService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Service
public class TokenAuthenticationServiceImpl implements TokenAuthenticationService {
	
	private TokenHandler tokenHandler = new TokenHandler();
	private static final Logger logger = Logger.getLogger(TokenAuthenticationServiceImpl.class);

	@Override
	public void addAuthentication(HttpServletResponse res, Authentication authentication) {
		User user = (User) authentication.getPrincipal();
		String jwt = tokenHandler.build(user.getUsername());
		//System.out.println("Token: " + jwt);
		res.addHeader(tokenHandler.HEADER_STRING, tokenHandler.TOKEN_PREFIX + jwt);
	}

	@Override
	public Authentication getAuthentication(HttpServletRequest request, UserDetailsService userDetailsService) throws ExpiredJwtException, UnsupportedJwtException, SignatureException, IllegalArgumentException {
		String token = request.getHeader(tokenHandler.HEADER_STRING);
		if (token != null) {
			String user = null;
			user = tokenHandler.parse(token);
			logger.info(user);
			if (user != null) {
				User authUser = (User) userDetailsService.loadUserByUsername(user);
				logger.info(authUser);
				return new UsernamePasswordAuthenticationToken(authUser, null, emptyList());
			}
			return null;
		}
		return null;
	}
}

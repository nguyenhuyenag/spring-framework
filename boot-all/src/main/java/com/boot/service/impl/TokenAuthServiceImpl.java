//package com.boot.service.impl;
//
//import java.util.Collections;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpHeaders;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Service;
//
//import com.boot.entity.User;
//import com.boot.filter.TokenHandler;
//import com.boot.service.TokenAuthService;
//
//@Service
//public class TokenAuthServiceImpl implements TokenAuthService {
//
//	private static final Logger LOGGER = LoggerFactory.getLogger(TokenAuthServiceImpl.class);
//
//	@Override
//	public Authentication getAuthentication(HttpServletRequest request, UserDetailsService userDetailsService) {
//		String token = request.getHeader(HttpHeaders.AUTHORIZATION);
//		if (StringUtils.isEmpty(token) || !token.startsWith(TokenHandler.PREFIX)) {
//			LOGGER.info("Couldn't find Bearer string");
//			return null;
//		}
//		String username = TokenHandler.getUsername(token);
//		if (StringUtils.isEmpty(username)) {
//			return null;
//		}
//		User user = (User) userDetailsService.loadUserByUsername(username);
//		return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
//	}
//}

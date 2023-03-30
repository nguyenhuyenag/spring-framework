package com.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.util.TokenHandler;

public class JWTAuthenticationFilter extends OncePerRequestFilter {

	private static final Logger LOG = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

	@Autowired
	private UserDetailsService userDetailsService;

	public JWTAuthenticationFilter(UserDetailsService service) {
		this.userDetailsService = service;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		String header = req.getHeader(HttpHeaders.AUTHORIZATION);
		if (header != null && header.startsWith(TokenHandler.TOKEN_PREFIX)) {
			String jwt = header.replace(TokenHandler.TOKEN_PREFIX, "");
			DecodedJWT verify = TokenHandler.verifyJWT(jwt);
			if (verify == null) {
				throw new JWTDecodeException("Invalid JWT token");
			}
			DecodedJWT decodedJWT = TokenHandler.decodedJWT(jwt);
			String username = TokenHandler.getSubject(decodedJWT);
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (StringUtils.isNotEmpty(username) && auth == null) {
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
				if (TokenHandler.validateToken(userDetails, username, decodedJWT)) {
					UsernamePasswordAuthenticationToken authToken = getAuthentication(userDetails, decodedJWT);
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
					LOG.info("Authenticated user " + username + ", setting security context");
					SecurityContextHolder.getContext().setAuthentication(authToken);
				}
			}
		} else {
			LOG.warn("Couldn't find bearer string, will ignore the header");
		}
		chain.doFilter(req, res);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(UserDetails userDetails, DecodedJWT decodedJWT) {
		final Collection<GrantedAuthority> authorities = new ArrayList<>();
		Claim claims = TokenHandler.getClaim(decodedJWT);
		if (claims != null) {
			Arrays.stream(claims.asString().split(",")).forEach(t -> {
				authorities.add(new SimpleGrantedAuthority(t));
			});
		}
		return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
	}

}

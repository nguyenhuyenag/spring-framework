package com.security;

import java.util.Base64;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.entity.Role;
import com.exception.HandlerException;
import com.service.impl.UserDetailsServiceImpl;
import com.util.DateTimeUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtProvider {

	private static String SECRET_KEY;
	public static final String TOKEN_PREFIX = "Bearer ";
	private static final String SECRET = "JWT_TOKEN_SECRET";

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@PostConstruct
	protected void init() {
		SECRET_KEY = Base64.getEncoder().encodeToString(SECRET.getBytes());
	}

	public String buildToken(String username, Set<Role> roles) {
		Claims claims = Jwts.claims().setSubject(username);
		claims.put("auth", roles.stream() //
				.map(s -> new SimpleGrantedAuthority(s.getAuthority())) //
				.filter(Objects::nonNull) //
				.collect(Collectors.toList()));
		Date now = new Date();
		Date expiration = new Date(System.currentTimeMillis() + DateTimeUtils.ONE_MINUTE);
		return Jwts.builder() //
				.setClaims(claims) //
				.setIssuedAt(now) //
				.setExpiration(expiration) //
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY) // 256
				.compact();
	}

	public Authentication getAuthentication(String token) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(getUsername(token));
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}

	public String getUsername(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
	}

	public String resolveToken(HttpServletRequest req) {
		String bearerToken = req.getHeader(HttpHeaders.AUTHORIZATION);
		if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
			return bearerToken.substring(7);
		}
		return StringUtils.EMPTY;
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			throw new HandlerException("Expired or invalid JWT token", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

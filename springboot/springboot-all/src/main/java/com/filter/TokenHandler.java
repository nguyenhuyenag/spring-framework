package com.filter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;

import com.util.DateTimeUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenHandler {

	public static final String PREFIX 			= "Bearer ";
	public static final String TOKEN_EXPIRES 	= "Token expires";
	private static final String SECRET 			= "JWT_TOKEN_SECRET";
	private static final byte[] SECRET_ARRAY 	= SECRET.getBytes();
	private static final long EXPIRATION_TIME	= DateTimeUtils.ONE_HOUR;

	private static final String ISS = "echisan";
	private static final String ROLE_CLAIMS = "rol";

	/**
	 * Build token from username
	 * @param username is username
	 * @return jwt
	 */
	public static String buildToken(String username, String role) {
		if (StringUtils.isEmpty(username)) {
			throw new IllegalArgumentException("Can't create JWT token without username!");
		}
		Map<String, Object> map = new HashMap<>();
		map.put(ROLE_CLAIMS, role);
		return Jwts.builder() //
				.signWith(SignatureAlgorithm.HS512, SECRET_ARRAY) //
				.setClaims(map) //
				.setIssuer(ISS) //
				.setSubject(username) //
				.setIssuedAt(new Date()) //
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) //
				.compact();
	}

	private static Claims getTokenBody(String token) {
		return Jwts.parser().setSigningKey(SECRET_ARRAY).parseClaimsJws(token).getBody();
	}

	/**
	 * Get username from jwt
	 * @param jwt token
	 * @return username
	 */
	public static String getUsername(String jwt) {
		return getTokenBody(jwt).getSubject();
	}

	public static String getRole(String token) {
		return (String) getTokenBody(token).get(ROLE_CLAIMS);
	}

	/**
	 * JWT is Expiration
	 * @param jwt is token
	 * @return {@code true} if token is expiration, otherwise {@code false}
	 */
	public static boolean isExpiration(String jwt) {
		try {
			return getTokenBody(jwt).getExpiration().before(new Date());
		} catch (ExpiredJwtException e) {
			return true;
		}
	}
	
	/**
	 * Get JWT from {@code HttpServletRequest}
	 * @param req is HttpServletRequest
	 * @return JWT or {@link StringUtils#EMPTY} if the request does not have a header
	 */
	public static String getJwtFromRequest(HttpServletRequest req) {
		String token = req.getHeader(HttpHeaders.AUTHORIZATION);
		if (StringUtils.isNotEmpty(token)) {
			return token.replace(TokenHandler.PREFIX, "");
		}
		return StringUtils.EMPTY;
	}

}

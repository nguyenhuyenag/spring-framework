package com.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.util.DateTimeUtils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenHandler {
	
	public static final String TOKEN_PREFIX 	= 	"Bearer ";
	public static final String TOKEN_EXPIRES 	= 	"Token expires";

	private static final Date EXPIRATION_TIME 	= 	DateTimeUtils.getLaterDate(DateTimeUtils.ONE_MINUTE);
	private static final String SECRET 			= 	"JWT_TOKEN_SECRET";
	private static final byte[] SECRET_BYTES 	= 	SECRET.getBytes(StandardCharsets.UTF_8);

	/**
	 * Build token from username
	 * @param username is username
	 * @return jwt token
	 */
	public static String buildToken(String username) {
		if (StringUtils.isEmpty(username)) {
			throw new IllegalArgumentException("Cannot create JWT token without username");
		}
		return Jwts.builder() //
				.setSubject(username) //
				.setExpiration(EXPIRATION_TIME) //
				.signWith(SignatureAlgorithm.HS512, SECRET_BYTES) //
				.compact();
	}

	/**
	 * Parse token to username
	 * @param token is jwt token
	 * @return username
	 */
	public static String getUsername(String token) {
		if (StringUtils.isEmpty(token)) {
			return StringUtils.EMPTY;
		}
		return Jwts.parser() //
				.setSigningKey(SECRET_BYTES) //
				.parseClaimsJws(token.replace(TOKEN_PREFIX, StringUtils.EMPTY)) //
				.getBody() //
				.getSubject();
	}
	
}
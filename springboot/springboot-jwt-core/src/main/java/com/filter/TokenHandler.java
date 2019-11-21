package com.filter;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.util.DateTimeUtils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenHandler {

	public static final String TOKEN_PREFIX 	= 	"Bearer ";
	public static final String TOKEN_EXPIRES 	= 	"Token expires";

	private static final long EXPIRATION_TIME 	=	DateTimeUtils.ONE_MINUTE / 2;
	private static final String SECRET 			= 	"JWT_TOKEN_SECRET";
	private static final byte[] SECRET_ARRAY 	= 	SECRET.getBytes(StandardCharsets.UTF_8);

	/**
	 * @param username
	 * @return token
	 */
	public static String buildToken(String username) {
		if (StringUtils.isEmpty(username)) {
			throw new IllegalArgumentException("Cannot create JWT Token without username");
		}
		return Jwts.builder() //
				.setSubject(username) //
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) //
				.signWith(SignatureAlgorithm.HS512, SECRET_ARRAY) //
				.compact();
	}

	/**
	 * @param token
	 * @return username
	 */
	public static String parseToken(String token) {
		if (StringUtils.isEmpty(token)) {
			return StringUtils.EMPTY;
		}
		return Jwts.parser() //
				.setSigningKey(SECRET_ARRAY) //
				.parseClaimsJws(token.replace(TOKEN_PREFIX, StringUtils.EMPTY)) //
				.getBody() //
				.getSubject();
	}

}

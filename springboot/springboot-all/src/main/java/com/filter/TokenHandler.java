package com.filter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.util.DateTimeUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenHandler {

	public static final String PREFIX 			= 	"Bearer ";
	public static final String TOKEN_EXPIRES 	= 	"Token expires";
	private static final String SECRET 			= 	"JWT_TOKEN_SECRET";
	private static final byte[] SECRET_ARRAY 	= 	SECRET.getBytes();
	private static final long EXPIRATION_TIME 	= 	DateTimeUtils.ONE_MINUTE * 30; // 30 phút
	
	
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

//	/**
//	 * Get username from token
//	 * @param token jwt token
//	 * @return username
//	 */
//	public static String getUsername(String token) {
//		try {
//			// if (StringUtils.isEmpty(token)) { return StringUtils.EMPTY; }
//			return Jwts.parser() //
//					.setSigningKey(SECRET_ARRAY) //
//					.parseClaimsJws(token.replace(PREFIX, StringUtils.EMPTY)) //
//					.getBody() //
//					.getSubject();
//		} catch (UnsupportedJwtException | MalformedJwtException | SignatureException | ExpiredJwtException
//				| IllegalArgumentException e) {
//			e.printStackTrace();
//		}
//		return StringUtils.EMPTY;
//	}

	private static Claims getTokenBody(String token) {
		return Jwts.parser().setSigningKey(SECRET_ARRAY).parseClaimsJws(token).getBody();
	}
	
	public static String getUsername(String token) {
		return getTokenBody(token).getSubject();
	}

	public static String getUserRole(String token) {
		return (String) getTokenBody(token).get(ROLE_CLAIMS);
	}

	public static boolean isExpiration(String token) {
		try {
			return getTokenBody(token).getExpiration().before(new Date());
		} catch (ExpiredJwtException e) {
			return true;
		}
	}

	
}

package com.util;

import java.util.Date;
import java.util.StringJoiner;
import java.util.function.Function;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenHandler {

	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String AUTHORITIES_KEY = "scopes";
	public static final String SIGNING_KEY = "JWT_TOKEN_SECRET";
	private static final Date EXPIRATION_TIME = TimeUtils.after().day(1);

	public static Claims getClaims(String token) {
		return Jwts.parser() //
				.setSigningKey(SIGNING_KEY) //
				.parseClaimsJws(token) //
				.getBody();
	}

	private static <T> T getClaims(String token, Function<Claims, T> fClaims) {
		Claims claims = getClaims(token);
		return fClaims.apply(claims);
	}

	public static String getUsername(String token) {
		return getClaims(token, Claims::getSubject);
	}

	private static Date getExpiration(String token) {
		return getClaims(token, t -> t.getExpiration());
	}

//	private static boolean isTokenExpired(String token) {
//		Date expiration = getExpiration(token);
//		return expiration.before(new Date());
//	}

	public static boolean isAlive(String token) {
		Date expiration = getExpiration(token);
		return expiration.after(new Date());
	}

	public static String generateToken(Authentication authentication) {
		// String authorities = authentication.getAuthorities().stream() //
		// 		.map(t -> t.getAuthority()) //
		// 		.collect(Collectors.joining(","));
		StringJoiner authorities = new StringJoiner(",");
		authentication.getAuthorities().stream().forEach(t -> {
			authorities.add(t.getAuthority());
		});
		// System.out.println("authorities: " + authorities);
		return Jwts.builder() //
				.setSubject(authentication.getName()) //
				.claim(AUTHORITIES_KEY, authorities.toString()) //
				.signWith(SignatureAlgorithm.HS512, SIGNING_KEY) //
				.setIssuedAt(TimeUtils.now()) //
				.setExpiration(EXPIRATION_TIME) //
				.compact();
	}

	public static boolean validateToken(UserDetails userDetails, String token) {
		String username = getUsername(token);
		return (isAlive(token) && username.equals(userDetails.getUsername()));
	}

}

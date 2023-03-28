package com.util;

import java.util.Date;
import java.util.StringJoiner;
import java.util.concurrent.TimeUnit;
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
	private static final long EXPIRATION_TIME = TimeUnit.MINUTES.toMillis(1);

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
	
	public static boolean validateToken(UserDetails userDetails, String token) {
		String username = getUsername(token);
		return (isAlive(token) && username.equals(userDetails.getUsername()));
	}

	public static String generateToken(Authentication authentication) {
		// String authorities = authentication.getAuthorities().stream() //
		// 		.map(t -> t.getAuthority()) //
		// 		.collect(Collectors.joining(","));
		StringJoiner authorities = new StringJoiner(",");
		authentication.getAuthorities().stream().forEach(t -> {
			authorities.add(t.getAuthority());
		});
		System.out.println("generateToken: " + authorities);
		return Jwts.builder() //
				.setSubject(authentication.getName()) 			  // username
				.claim(AUTHORITIES_KEY, authorities.toString())   // authorities = "ROLE_USER,ROLE_ADMIN"
				.signWith(SignatureAlgorithm.HS512, SIGNING_KEY)  //
				.setIssuedAt(new Date()) //
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) //
				.compact();
	}
	
	public static String generateTokenByUsernameAndAuthorities(String username, String authorities) {
		System.out.println("generateTokenByUsernameAndAuthorities: " + authorities);
		return Jwts.builder() //
				.setSubject(username) //
				.claim(AUTHORITIES_KEY, authorities.toString()) //
				.signWith(SignatureAlgorithm.HS512, SIGNING_KEY) //
				.setIssuedAt(new Date()) //
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) //
				.compact();
	}

}

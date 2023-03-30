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

	public static final String TOKEN_PREFIX 	= "Bearer ";
	public static final String AUTHORITIES_KEY 	= "scopes";
	public static final String SECRET_KEY 		= "JWT_TOKEN_SECRET";
	private static final long EXPIRATION_TIME 	= TimeUnit.DAYS.toMillis(1);

	public static Claims getClaims(String token) {
		Claims claims = null;
		try {
			claims = Jwts.parser() //
					.setSigningKey(SECRET_KEY) //
					.parseClaimsJws(token) //
					.getBody();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// claims = {sub=user, scopes=ROLE_USER, iat=1680150685, exp=1680237085}
		return claims;
	}
	
	private static <T> T extractData(String token, Function<Claims, T> func) {
		Claims claims = getClaims(token);
		return func.apply(claims); // call claims.func()
	}
	
	public static boolean isAlive(String token) {
		Date expiration = extractData(token, Claims::getExpiration);
		return expiration.after(new Date());
	}
	
	public static String getUsername(String token) {
		return extractData(token, Claims::getSubject);
	}

//	private static Date getExpiration(String token) {
//		return getClaims(token, t -> t.getExpiration());
//	}
	
	public static boolean validateToken(UserDetails userDetails, String token) {
		String username = getUsername(token);
		return (isAlive(token) && username.equals(userDetails.getUsername()));
	}

	public static String generateToken(Authentication authentication) {
		StringJoiner authorities = new StringJoiner(",");
		authentication.getAuthorities().stream().forEach(t -> {
			authorities.add(t.getAuthority());
		});
		// authorities = "ROLE_USER,ROLE_ADMIN"
		return Jwts.builder() //
				.setSubject(authentication.getName()) // username
				.claim(AUTHORITIES_KEY, authorities.toString()) //
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY) //
				.setIssuedAt(new Date()) //
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) //
				.compact();
	}
	
	public static String generateTokenByUsernameAndAuthorities(String username, String authorities) {
		System.out.println("generateTokenByUsernameAndAuthorities: " + authorities);
		return Jwts.builder() //
				.setSubject(username) //
				.claim(AUTHORITIES_KEY, authorities.toString()) //
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY) //
				.setIssuedAt(new Date()) //
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) //
				.compact();
	}

}

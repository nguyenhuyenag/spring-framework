package com.filter;

import java.util.Date;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.util.TimeUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenHandler {

	public static final String TOKEN_PREFIX 	= "Bearer ";
	public static final String AUTHORITIES_KEY 	= "scopes";
	public static final String SIGNING_KEY 		= "JWT_TOKEN_SECRET";
	private static final Date EXPIRATION_TIME 	= TimeUtils.after().hour(1);

	private static <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	public static Claims getAllClaimsFromToken(String token) {
		return Jwts.parser() //
				.setSigningKey(SIGNING_KEY) //
				.parseClaimsJws(token) //
				.getBody();
	}
	
	public static String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	private static Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, t -> t.getExpiration());
	}

	private static boolean isTokenExpired(String token) {
		Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	public static String generateToken(Authentication authentication) {
		String authorities = authentication.getAuthorities().stream() //
				.map(GrantedAuthority::getAuthority) //
				.collect(Collectors.joining(","));
		return Jwts.builder() //
				.setSubject(authentication.getName()) //
				.claim(AUTHORITIES_KEY, authorities) //
				.signWith(SignatureAlgorithm.HS512, SIGNING_KEY) //
				.setIssuedAt(TimeUtils.now()) //
				.setExpiration(EXPIRATION_TIME) //
				.compact();
	}

	public static Boolean validateToken(UserDetails userDetails, String token) {
		String username = getUsernameFromToken(token);
		return (!isTokenExpired(token) && username.equals(userDetails.getUsername()));
	}

}

package com.filter;

import java.nio.charset.StandardCharsets;
import java.util.Calendar;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenHandler {

	static final int EXPIRATIONTIME 		= 1; // 1 days
	public final String TOKEN_PREFIX 		= "Bearer ";
	public final String HEADER_STRING 		= "Authorization";
	private static final String SECRET 		= "JWT_TOKEN_SECRET";
	private final byte[] SECRET_BYTES 		= SECRET.getBytes(StandardCharsets.UTF_8);
	
	//new Date(System.currentTimeMillis() + 10000) => 10s
	public String build(String username) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, EXPIRATIONTIME);
		return Jwts.builder()
				.setSubject(username)
				.setExpiration(calendar.getTime())
				.signWith(SignatureAlgorithm.HS512, SECRET_BYTES)
				.compact();
	}

	// parse the token
	public String parse(String token) {
		String user = Jwts.parser()
				.setSigningKey(SECRET_BYTES)
				.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
				.getBody()
				.getSubject();
		return user;
	}

}

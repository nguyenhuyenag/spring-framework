package com.util;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.security.core.userdetails.UserDetails;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

/*-
 	iss (issuer) 			Tổ chức phát hành của Token
	sub (subject) 			Chủ đề Token
	aud (audience) 			Đối tượng sử dụng Token
	exp (expired time)		Thời điểm token sẽ hết hạn
	nbf (not before time) 	Token chưa hợp lệ trước thời điểm này
	iat (issued at) 		Thời điểm token sẽ được phát hành, tính theo UNIX time
	jti 					ID của JWT
 */
public class TokenHandler {

	public static final String BEARER 				= "Bearer ";
	public static final String AUTHORITIES_KEY 		= "scopes";
	private static final String ISSUER 				= "JWT_ISSUER";
	private static final String SECRET_KEY 			= "JWT_26A879D65E22";
	private static final long EXPIRATION_TIME 		= TimeUnit.DAYS.toMillis(1);
	
	private static final Algorithm ALGORITHM 	= Algorithm.HMAC512(SECRET_KEY);;
	private static JWTVerifier verifier = JWT.require(ALGORITHM).withIssuer(ISSUER).build();

//	public static Claims getClaims(String token) {
//		Claims claims = null;
//		try {
//			claims = Jwts.parser() //
//					.setSigningKey(SECRET_KEY) //
//					.parseClaimsJws(token) //
//					.getBody();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		// claims = {sub=user, scopes=ROLE_USER, iat=1680150685, exp=1680237085}
//		return claims;
//	}
//	
//	private static <T> T extractData(String token, Function<Claims, T> func) {
//		Claims claims = getClaims(token);
//		return func.apply(claims); // call claims.func()
//	}
//	public static boolean isAlive(String token) {
//		Date expiration = extractData(token, Claims::getExpiration);
//		return expiration.after(new Date());
//	}
//
//	public static String getUsername(String token) {
//		return extractData(token, Claims::getSubject);
//	}
//	private static Date getExpiration(String token) {
//		return getClaims(token, t -> t.getExpiration());
//	}
//
//	public static String createJWT(String username, String authorities) {
//	// authorities = "ROLE_USER,ROLE_ADMIN"
//	return Jwts.builder() //
//			.setSubject(username) //
//			.claim(AUTHORITIES_KEY, authorities) //
//			.signWith(SignatureAlgorithm.HS512, SECRET_KEY) //
//			.setIssuedAt(new Date()) //
//			.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) //
//			.compact();
//}
	
	public static String createJWT(String username, String authorities) {
		return JWT.create() //
				.withSubject(username) //
				.withClaim(AUTHORITIES_KEY, authorities) //
				.withIssuer(ISSUER) //
				.withIssuedAt(new Date()) //
				.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) //
				.sign(ALGORITHM);
	}
	
	public static DecodedJWT verifyJWT(String jwt) {
		try {
			DecodedJWT verify = verifier.verify(jwt);
			return verify;
		} catch (JWTVerificationException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static DecodedJWT decodedJWT(String jwt) {
		try {
			return JWT.decode(jwt);
		} catch (JWTDecodeException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getSubject(DecodedJWT decodedJWT) {
		return decodedJWT.getSubject();
	}

	public static boolean validateToken(UserDetails userDetails, String username, DecodedJWT decodedJWT) {
		return (isJWTAlive(decodedJWT) && userDetails.getUsername().equals(username));
	}

	private static boolean isJWTAlive(DecodedJWT decodedJWT) {
		Date expiresAt = decodedJWT.getExpiresAt();
		if (expiresAt != null) {
			return expiresAt.after(new Date());
		}
		return false;
	}

	public static Claim getClaim(DecodedJWT decodedJWT) {
		return decodedJWT.getClaim(AUTHORITIES_KEY);
	}
	
	public static void main(String args[]) throws InterruptedException {
		String jwt = createJWT("dev1", null);
		System.out.println("JWT: " + jwt);
		DecodedJWT decodedJWT = verifyJWT(jwt);
		if (decodedJWT == null) {
			System.out.println("JWT Verification Failed");
		}
		decodedJWT = decodedJWT(jwt);
		if (decodedJWT != null) {
			System.out.println("Subject : " + decodedJWT.getSubject());
			System.out.println("Issued At : " + decodedJWT.getIssuedAt());
			System.out.println("Expires At : " + decodedJWT.getExpiresAt());
			System.out.println("Is Alive : " + isJWTAlive(decodedJWT));
			// Claim claim = getClaim(decodedJWT);
			System.out.println("Claim : " + getClaim(decodedJWT));
			// createAuthorities(claim);
		}
	}

}

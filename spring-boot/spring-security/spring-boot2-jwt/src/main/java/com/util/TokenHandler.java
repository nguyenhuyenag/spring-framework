package com.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/*-
    Build JWT sử dụng thư viện: https://mvnrepository.com/artifact/com.auth0/java-jwt
        iss (issuer) 				Tổ chức phát hành của Token
        sub (subject) 				Chủ đề Token
        aud (audience)				Đối tượng sử dụng Token
        exp (expired time)			Thời điểm token sẽ hết hạn
        nbf (not before time)		Token chưa hợp lệ trước thời điểm này
        iat (issued at) 			Thời điểm token sẽ được phát hành, tính theo UNIX time
        jti 						ID của JWT
 */
@Slf4j
public class TokenHandler {

    public static final String BEARER = "Bearer ";
    public static final String AUTHORITIES_KEY = "scopes";
    private static final String ISSUER = "JWT_ISSUER";
    private static final String SECRET_KEY = "cThl2O+Ogi/nkciMZpyIlXw6l4Zsh7dyUZfExYssEJaegAmbsInT6dUVR5n9pjA9";
    private static final long EXPIRATION_TIME = TimeUnit.DAYS.toMillis(1);

    private static final Algorithm ALGORITHM = Algorithm.HMAC384(SECRET_KEY);
    private static final JWTVerifier verifier = JWT.require(ALGORITHM)
            .withIssuer(ISSUER)
            .build();

    public static String extractJWT(HttpServletRequest request) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header != null && header.startsWith(TokenHandler.BEARER)) {
            return header.replace(TokenHandler.BEARER, "");
        }
        return "";
    }

    public static String createJWT(String username, String authorities) {
        return JWT.create() //
                .withSubject(username) // (1) xem hàm getSubject()
                .withClaim(AUTHORITIES_KEY, authorities) //
                .withIssuer(ISSUER) //
                .withIssuedAt(new Date()) //
                .withJWTId(UUID.randomUUID().toString())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) //
                .sign(ALGORITHM);
    }

//    public static Map<String, Boolean> validateToken(ValidateTokenRequest request) {
//        boolean value = false;
//        try {
//            DecodedJWT decode = verifier.verify(request.getToken().trim());
//            value = (decode != null && decode.getExpiresAt().after(new Date()));
//        } catch (JWTVerificationException e) {
//            log.error("JWT Verification Failed: {}", e.getMessage());
//        }
//        Map<String, Boolean> result = new HashMap<>();
//        result.put("validate", value);
//        return result;
//    }

    public static boolean validateJWT(String jwt) {
        try {
            DecodedJWT decoded = verifier.verify(jwt);
            Date expiresAt = decoded.getExpiresAt();
            if (expiresAt == null) {
                log.warn("JWT has no expiration claim (exp). Rejecting token.");
                return false;
            }
            return expiresAt.getTime() > System.currentTimeMillis();
        } catch (JWTVerificationException e) {
            log.error("JWT verification failed: {}", e.getMessage());
            return false;
        }
    }

    // Token lỗi vẫn decode được, chỉ là không verify được
    public static DecodedJWT decodedJWT(String jwt) {
        try {
            return JWT.decode(jwt);
        } catch (JWTDecodeException e) {
            log.error("JWT decode failed: {}", e.getMessage());
        }
        return null;
    }

    /**
     * (1): It's 'username' or 'email',...
     */
    public static String getSubject(DecodedJWT decodedJWT) {
        if (decodedJWT == null) {
            log.warn("Decoded JWT is null. Cannot get subject.");
            return null;
        }
        return decodedJWT.getSubject();
    }

//    private static boolean isJWTAlive(DecodedJWT decodedJWT) {
//        Date expiresAt = decodedJWT.getExpiresAt();
//        return expiresAt != null && expiresAt.after(new Date());
//    }

    public static Claim getClaim(DecodedJWT decodedJWT) {
        return decodedJWT.getClaim(AUTHORITIES_KEY);
    }

//    public static void main(String[] args) throws InterruptedException {
//        String jwt = createJWT("dev1", null);
//        System.out.println("JWT: " + jwt);
//        DecodedJWT decodedJWT = decodedJWT(jwt);
//        if (decodedJWT == null) {
//            System.out.println("JWT Verification Failed");
//        }
//        decodedJWT = decodedJWT(jwt);
//        if (decodedJWT != null) {
//            System.out.println("Subject : " + decodedJWT.getSubject());
//            System.out.println("Issued At : " + decodedJWT.getIssuedAt());
//            System.out.println("Expires At : " + decodedJWT.getExpiresAt());
//            System.out.println("Is Alive : " + isJWTAlive(decodedJWT));
//            // Claim claim = getClaim(decodedJWT);
//            System.out.println("Claim : " + getClaim(decodedJWT));
//            // createAuthorities(claim);
//        }
//    }

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

    //	public static boolean validateToken(UserDetails userDetails, String username, DecodedJWT decodedJWT) {
//		return (isJWTAlive(decodedJWT) && userDetails.getUsername().equals(username));
//	}

}

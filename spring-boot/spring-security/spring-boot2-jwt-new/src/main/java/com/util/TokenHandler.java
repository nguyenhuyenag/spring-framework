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
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

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
@Component
public class TokenHandler {

    public static final String BEARER = "Bearer ";
    public static final String AUTHORITIES_KEY = "scopes";

    private static Algorithm algorithm() {
        return Algorithm.HMAC384(ConfigReader.JWT_SECRET_KEY);
    }

    private static JWTVerifier verifier() {
        return JWT.require(algorithm()).withIssuer(ConfigReader.JWT_ISSUER).build();
    }

    public static String createJwt(String username, String authorities) {
        // @formatter:off
        return JWT.create()
                .withSubject(username)
                .withClaim(AUTHORITIES_KEY, authorities)
                .withIssuer(ConfigReader.JWT_ISSUER)
                .withIssuedAt(Instant.now())
                .withJWTId(UUID.randomUUID().toString())
                .withExpiresAt(
                    Date.from(Instant.now().plus(ConfigReader.JWT_EXPIRATION_TIME, ChronoUnit.SECONDS))
                )
                .sign(algorithm());
        // @formatter:on
    }

    public static boolean validateJwt(String jwt) {
        try {
            DecodedJWT decoded = verifier().verify(jwt);
            Date expiresAt = decoded.getExpiresAt();
            if (expiresAt == null) {
                log.warn("JWT has no expiration claim (exp). Rejecting token.");
                return false;
            }
            return expiresAt.getTime() > System.currentTimeMillis();
        } catch (JWTVerificationException e) {
            log.error("JWT verification failed: {}", e.getMessage());
        }
        return false;
    }

    // Token lỗi vẫn decode được, nhưng verify false
    public static DecodedJWT decodedJwt(String jwt) {
        try {
            return JWT.decode(jwt);
        } catch (JWTDecodeException e) {
            log.error("JWT decode failed: {}", e.getMessage());
        }
        return null;
    }

    /**
     * (1): Subject is 'username' or 'email',...
     */
    public static String getSubject(DecodedJWT decoded) {
        if (decoded == null) {
            log.warn("Decoded JWT is null. Cannot get subject.");
            return null;
        }
        return decoded.getSubject();
    }

    public static Claim getClaim(DecodedJWT decoded) {
        return decoded.getClaim(AUTHORITIES_KEY);
    }

    public static String getJwtFromRequest(HttpServletRequest request) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header != null && header.startsWith(BEARER)) {
            return header.replace(BEARER, "");
        }
        return "";
    }

}

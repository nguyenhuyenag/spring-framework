//package com.util;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.JWTVerifier;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.exceptions.JWTDecodeException;
//import com.auth0.jwt.exceptions.JWTVerificationException;
//import com.auth0.jwt.interfaces.Claim;
//import com.auth0.jwt.interfaces.DecodedJWT;
//import com.entity.User;
//import com.nimbusds.jose.*;
//import com.nimbusds.jose.crypto.MACSigner;
//import com.nimbusds.jwt.JWTClaimsSet;
//import org.springframework.http.HttpHeaders;
//
//import javax.servlet.http.HttpServletRequest;
//import java.time.Instant;
//import java.time.temporal.ChronoUnit;
//import java.util.Date;
//import java.util.UUID;
//import java.util.concurrent.TimeUnit;
//
///*-
//    Build JWT sử dụng thư viện: https://mvnrepository.com/artifact/com.nimbusds/nimbus-jose-jwt
//
//        iss (issuer) 				Tổ chức phát hành của Token
//        sub (subject) 				Chủ đề Token
//        aud (audience)				Đối tượng sử dụng Token
//        exp (expired time)			Thời điểm token sẽ hết hạn
//        nbf (not before time)		Token chưa hợp lệ trước thời điểm này
//        iat (issued at) 			Thời điểm token sẽ được phát hành, tính theo UNIX time
//        jti 						ID của JWT
// */
//public class Token2Handler {
//
//    public static final String BEARER = "Bearer ";
//    public static final String AUTHORITIES_KEY = "scopes";
//    private static final String ISSUER = "JWT_ISSUER";
//    private static final String SIGNER_KEY = "JWT_26A879D65E22";
//    private static final long EXPIRATION_TIME = TimeUnit.DAYS.toMillis(1);
//
//    private String generateToken(User user) {
//        // Header
//        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
//
//        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
//                .subject(user.getUsername())
//                .issuer("dev.com")
//                .issueTime(new Date())
//                .expirationTime(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//                .jwtID(UUID.randomUUID().toString())
//                // .claim("scope", buildScope(user))
//                .build();
//
//        // Payload
//        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
//
//        JWSObject jwsObject = new JWSObject(header, payload);
//
//        try {
//            // Ký token
//            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
//            return jwsObject.serialize();
//        } catch (JOSEException e) {
//            // log.error("Cannot create token", e);
//            throw new RuntimeException(e);
//        }
//    }
//
//}

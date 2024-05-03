package com.service;

import com.dto.request.AuthenticationRequest;
import com.dto.request.IntrospectRequest;
import com.dto.request.LogoutRequest;
import com.dto.request.RefreshRequest;
import com.dto.response.AuthenticationResponse;
import com.dto.response.IntrospectResponse;
import com.entity.InvalidatedToken;
import com.entity.User;
import com.enums.ErrorCode;
import com.exception.AppException;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import com.nimbusds.jwt.SignedJWT;
import com.repository.InvalidatedTokenRepository;
import com.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
@Service
public class AuthenticationService {

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationService.class);

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private InvalidatedTokenRepository invalidatedTokenRepository;

    @NonFinal // Để @RequiredArgsConstructor không DI field này
    @Value("${jwt.signerKey}")
    private String signerKey;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        // Check password
        boolean matches = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if (!matches) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
        String token = generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                // .authenticated(true)
                .build();
    }

    private String generateToken(User user) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        long expirationTime = Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli();
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("huyennv")
                .issueTime(new Date())
                .expirationTime(new Date(expirationTime))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope", buildScope(user))
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(signerKey.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            LOG.error("Cannot create token", e);
            throw new RuntimeException(e);
        }
    }

    public IntrospectResponse introspect(IntrospectRequest request) throws ParseException {
        String token = request.getToken();
        boolean isValid = verifyToken(token);
        return IntrospectResponse.builder()
                .valid(isValid)
                .build();
    }

    public void showTokenInfoWithoutVerify(String token) throws ParseException {
        JWT jwt = JWTParser.parse(token);
        Map<String, Object> claims = jwt.getJWTClaimsSet().getClaims();
        claims.forEach((key, value) -> {
            System.out.printf("%s: %s\n", key, value);
        });
    }

    public boolean verifyToken(String token) {
        try {
            showTokenInfoWithoutVerify(token);
            // Parse & verify
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(signerKey.getBytes());
            boolean verify = signedJWT.verify(verifier);
            Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
            return verify && expiryTime.after(new Date())
                    && !invalidatedTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID());
        } catch (ParseException | JOSEException e) {
            e.printStackTrace();
        }
        return false;
    }

    private SignedJWT verifyAndExtractToken(String token) throws JOSEException, ParseException {
        SignedJWT signedJWT = SignedJWT.parse(token);
        JWSVerifier verifier = new MACVerifier(signerKey.getBytes());

        boolean isVerified = signedJWT.verify(verifier);
        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        if (!(isVerified && expiryTime.after(new Date()))) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        if (invalidatedTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID())) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        return signedJWT;
    }

    // Set<Role> -> "ROLE_USER ROLE_ADMIN"
    private String buildScope(User user) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        if (!CollectionUtils.isEmpty(user.getRoles())) {
            user.getRoles().forEach(role -> {
                // Add role name to scope
                stringJoiner.add("ROLE_" + role.getName());
                // Add list permissions to scope
                if (!CollectionUtils.isEmpty(role.getPermissions())) {
                    role.getPermissions().forEach(permission -> stringJoiner.add(permission.getName()));
                }
            });
        }
        return stringJoiner.toString();
    }

    public void logout(LogoutRequest request) throws ParseException, JOSEException {
        var signToken = verifyAndExtractToken(request.getToken());

        String jit = signToken.getJWTClaimsSet().getJWTID();
        Date expiryTime = signToken.getJWTClaimsSet().getExpirationTime();

        InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                .id(jit)
                .expiryTime(expiryTime)
                .build();

        invalidatedTokenRepository.save(invalidatedToken);
    }

    public AuthenticationResponse refreshToken(RefreshRequest request)
            throws ParseException, JOSEException {
        // Get SignedJWT
        var signedJWT = verifyAndExtractToken(request.getToken());
        // Get token id
        var tokeId = signedJWT.getJWTClaimsSet().getJWTID();
        // Get expiration time
        var expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                .id(tokeId)
                .expiryTime(expiryTime)
                .build();

        // Logout this token
        invalidatedTokenRepository.save(invalidatedToken);

        // Get username & create new token
        var username = signedJWT.getJWTClaimsSet().getSubject();

        var user = userRepository.findByUsername(username).orElseThrow(
                () -> new AppException(ErrorCode.UNAUTHENTICATED)
        );

        var token = generateToken(user);

        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

}

package com.service;

import com.dto.request.AuthenticationRequest;
import com.dto.request.IntrospectRequest;
import com.dto.response.AuthenticationResponse;
import com.dto.response.IntrospectResponse;
import com.entity.User;
import com.enums.ErrorCode;
import com.exception.AppException;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
@Service
public class AuthenticationService {

    private final Logger LOG = LoggerFactory.getLogger(AuthenticationService.class);

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @NonFinal // Để @RequiredArgsConstructor không DI field này
    @Value("${jwt.signerKey}")
    private String signerKey;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
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
                .issuer("nguyenhuyenag")
                .issueTime(new Date())
                .expirationTime(new Date(expirationTime))
                // .jwtID(UUID.randomUUID().toString())
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

    public IntrospectResponse introspect(IntrospectRequest request) {
        String token = request.getToken();
        boolean isValid = verifyToken(token);
        return IntrospectResponse.builder()
                .valid(isValid)
                .build();
    }

    private boolean verifyToken(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
            JWSVerifier verifier = new MACVerifier(signerKey.getBytes());
            boolean verify = signedJWT.verify(verifier);
            return verify && expiryTime.after(new Date());
        } catch (ParseException | JOSEException e) {
            // throw new RuntimeException(e);
            e.printStackTrace();
        }
        return false;
    }

    // Set<Role> -> "ROLE_USER ROLE_ADMIN"
    private String buildScope(User user) {
        if (!CollectionUtils.isEmpty(user.getRoles())) {
            return String.join(" ", user.getRoles());
        }
        return StringUtils.EMPTY;
    }


}

package com.otp;

import com.eatthepath.otp.TimeBasedOneTimePasswordGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base32;

import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;

@Slf4j
public class OtpUtils {

    private static final Base32 BASE_32 = new Base32(0); // 0 means no padding
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    private static final String ALGORITHM = "HMACSHA1";

    public static String generateSecretKey() {
        byte[] bytes = new byte[10]; // 80 bits
        SECURE_RANDOM.nextBytes(bytes);
        return BASE_32.encodeToString(bytes); // No padding to remove
    }

    public static int generateOtp(String secretKey, int duration) {
        byte[] decodedKey = new Base32().decode(secretKey);
        Key key = new SecretKeySpec(decodedKey, ALGORITHM);
        try {
            TimeBasedOneTimePasswordGenerator totpGenerator //
                    = new TimeBasedOneTimePasswordGenerator(Duration.ofSeconds(duration));
            return totpGenerator.generateOneTimePassword(key, Instant.now());
        } catch (InvalidKeyException | NumberFormatException e) {
            log.error("Error verifyOtp: {}", e.getMessage(), e);
        }
        return -1;
    }

}

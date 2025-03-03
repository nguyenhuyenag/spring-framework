package com.otp;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base32;
import org.springframework.util.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Base64;

@Slf4j
public class QRUtils {

    private static final String EXTENSION = "png";
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;

    public static String generateSecretKey() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[10]; // 80 bits
        random.nextBytes(bytes);
        Base32 base32 = new Base32();
        return base32.encodeToString(bytes).replace("=", ""); // Remove padding
    }

    public static String createQRCode() {
        String company = "COMPANY";
        String accountName = "huyennv@gmail.com";
        String label = company + "-" + accountName;
        String secretKey = generateSecretKey();
        String otpAuthUrl = String.format("otpauth://totp/%s?secret=%s&issuer=%s", label, secretKey, company);
        return generateBase64QRCode(otpAuthUrl);
    }

    private static String generateBase64QRCode(String input) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            BitMatrix matrix = new MultiFormatWriter().encode(input, BarcodeFormat.QR_CODE, WIDTH, HEIGHT);
            MatrixToImageWriter.writeToStream(matrix, EXTENSION, bos);
            return Base64.getEncoder().encodeToString(bos.toByteArray());
        } catch (IOException | WriterException e) {
            log.error("Error generating QR code: {}", e.getMessage(), e);
        }
        return "";
    }

}

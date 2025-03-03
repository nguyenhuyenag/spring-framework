package com.controller;

import com.otp.OtpUtils;
import com.otp.QRUtils;
import com.util.JsonUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@RestController
public class OTPController {

    @GetMapping(value = "/generate-key")
    public ResponseEntity<?> generateKey() {
        Map<String, String> map = new HashMap<>();
        map.put("secret_key", OtpUtils.generateSecretKey());
        return ResponseEntity.ok(map);
    }

    @GetMapping(value = "/generate-otp", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<?> getFlux(String secretKey,
                           @RequestParam(value = "duration", defaultValue = "30") int duration) {
        return Flux.interval(Duration.ofSeconds(2))
                .map(t -> OtpUtils.generateOtp(secretKey, duration));
    }

    @GetMapping(value = "/generate-google-otp")
    public ResponseEntity<?> generateGoogleOtp() {
        Map<String, String> map = new HashMap<>();
        map.put("qr_code", QRUtils.createQRCode());
        return ResponseEntity.ok(JsonUtils.toJson(map));
    }

}

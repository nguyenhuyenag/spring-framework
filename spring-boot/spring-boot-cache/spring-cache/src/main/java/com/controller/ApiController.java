package com.controller;

import com.service.CacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final CacheService cacheService;

    @GetMapping("get-otp")
    private ResponseEntity<?> getOtp() {
        return ResponseEntity.ok(cacheService.generateOtp());
    }

    @GetMapping("verify-otp")
    private ResponseEntity<?> verifyOtp(String id, @RequestParam(value = "otp", defaultValue = "-1") int otp) {
        Map<String, String> map = new HashMap<>();
        map.put("status", Boolean.toString(cacheService.verifyOtp(id, otp)));
        return ResponseEntity.ok(map);
    }

}

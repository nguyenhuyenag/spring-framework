package com.controller;

import com.model.CacheData;
import com.service.CacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final CacheService cacheService;

    @GetMapping("get-otp")
    private ResponseEntity<?> getOtp() {
        CacheData cacheData = cacheService.generateOtp();
        return ResponseEntity.ok(cacheData);
    }

    @GetMapping("verify-otp")
    private ResponseEntity<?> verifyOtp() {
        return ResponseEntity.ok(null);
    }

}

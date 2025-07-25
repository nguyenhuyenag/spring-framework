package com.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ServerTestController {

    @PostMapping("/token")
    private ResponseEntity<?> test() {
        Map<String, Object> map = new HashMap<>();
        map.put("status", 1);
        map.put("access_token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJpdnVhZG1pbiIsIm5hbWUiOiJUcmFuZyBUaW4iLCJpYXQiOjE2OTk4MzgwMDAsImV4cCI6MTY5OTg0MTYwMH0.o2B1eqtqvhgkHqDe-Ra5rbC4zDNxZ8D3ktZcOAYqxDA");
        map.put("expired_in", 3600);
        map.put("error_message", null);
        return ResponseEntity.ok(map);
    }

    @PostMapping("/tra-cuu-loai-giay")
    private ResponseEntity<?> traCuuLoaiGiay() {
        Map<String, Object> map = new HashMap<>();
        map.put("status", -1);
        // map.put("data", Arrays.asList("07_2412310079", "08_2412310079"));
        map.put("error_message", "Thời gian không đúng định dạng");
        return ResponseEntity.ok(map);
    }

}

package com.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api")
public class ServerTestController {

    @PostMapping("/CToauth/oauth2/token")
    private ResponseEntity<?> test() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("token_type", "Bearer");
        map.put("access_token", "AAIgYTE5Yjg0MTI5MDA4N2NiYzE3NjI0NjkwOTVmMjA0YTCF1HMkS4ldRSXiGyXwvQrR8mhwoSntYLAdDe1QIt-OvLiGv2yYWnG1KX6WNjdPGWHYK7voAmif4h6aK_ttmbKR-eNkGj7RWmVS17s69691TWFJSy5D925awBxGt5tXvAlNI7tX87l8r-hnNJ4VeYip");
        map.put("scope", "tct_oauth_scope");
        map.put("expires_in", 3600);
        map.put("consented_on", 1753842363);

        return ResponseEntity.ok(map);
    }

}

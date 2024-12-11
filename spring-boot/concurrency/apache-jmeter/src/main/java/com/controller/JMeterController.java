package com.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class JMeterController {

    public String now() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.now().format(formatter);
    }

    @GetMapping("/get-time")
    public ResponseEntity<?> getTime() {
        String threadName = Thread.currentThread().getName();
        log.info("Thread={}", threadName);
        Map<String, String> result = new HashMap<>();
        result.put("time", now());
        result.put("thread", threadName);
        return ResponseEntity.ok(result);
    }

}

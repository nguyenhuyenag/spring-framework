package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Cấu hình RateLimitConfiguration.java
 */
@RestController
public class LimitRequests {

	@Autowired
	private com.google.common.util.concurrent.RateLimiter rateLimiter;

	@GetMapping("limit-requests")
	public ResponseEntity<?> limitRequests() {
		if (rateLimiter.tryAcquire()) {
			// Logic here
			return ResponseEntity.ok("Success!");
		}
		return ResponseEntity.ok(HttpStatus.TOO_MANY_REQUESTS);
	}
	
//	@io.github.resilience4j.ratelimiter.annotation.RateLimiter(name = "myRateLimiter")
//    @GetMapping("/limited-endpoint")
//    public String limitedEndpoint() {
//        return "This endpoint is rate-limited to 10 requests per second.";
//    }

}

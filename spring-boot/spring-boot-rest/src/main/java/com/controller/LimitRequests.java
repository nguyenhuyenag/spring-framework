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
	protected com.google.common.util.concurrent.RateLimiter rateLimiter;

	@GetMapping("limit-requests")
	public ResponseEntity<?> limitRequests() {
		// RateLimiter rateLimiter = RateLimiter.create(1.0); // Not working
		if (rateLimiter.tryAcquire()) {
			// Logic here
			return ResponseEntity.ok("Success!");
		}
		return ResponseEntity.ok(HttpStatus.TOO_MANY_REQUESTS);
	}

}

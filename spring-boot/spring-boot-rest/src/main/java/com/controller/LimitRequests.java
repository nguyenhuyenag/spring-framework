package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.util.concurrent.RateLimiter;

/**
 * Cấu hình RateLimitConfiguration.java
 */
@RestController
public class LimitRequests {

	@Autowired
	private RateLimiter rateLimiter;

	@Autowired
	@Qualifier("rate2Limiter")
	private RateLimiter rate2Limiter;

	@GetMapping("limit-requests")
	public String limitRequests() {
		if (rateLimiter.tryAcquire()) {
			// Logic here
			return "Success!";
		}
		return "Rate limit exceeded.";

	}

	@GetMapping("limit-requests-2")
	public String limitRequests2() {
		if (rate2Limiter.tryAcquire()) {
			return "Success!";
		}
		return "Rate limit exceeded.";

	}

}

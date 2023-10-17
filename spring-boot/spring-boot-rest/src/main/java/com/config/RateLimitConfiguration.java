package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RateLimitConfiguration {

	@Bean
	public com.google.common.util.concurrent.RateLimiter rateLimiterByGuava() {
		return com.google.common.util.concurrent.RateLimiter.create(1); // 1 requests per second
	}

}

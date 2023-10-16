package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.util.concurrent.RateLimiter;

@Configuration
public class RateLimitConfiguration {

	@Bean
	public RateLimiter rateLimiter() {
		return RateLimiter.create(10); // 10 requests per second
	}
	
	@Bean(name = "rate2Limiter")
	public RateLimiter rate2Limiter() {
		return RateLimiter.create(1); // 1 requests per second
	}

}

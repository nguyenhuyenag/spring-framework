package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*-
	@Bean 
	public Map<String, RateLimiter> rateLimiters() { 
		Map<String, RateLimiter> rateLimiters = new ConcurrentHashMap<>();
		rateLimiters.put("/api/slow-operation", new RateLimiter(5.0));
		rateLimiters.put("/api/fast-operation", new RateLimiter(20.0));
		return rateLimiters;
	}
	
	@Autowired
	private Map<String, RateLimiter> rateLimiters;
	
	RateLimiter rateLimiter = rateLimiters.get("/api/slow-operation");
	
 */
@Configuration
public class RateLimitConfiguration {

	@Bean
	public com.google.common.util.concurrent.RateLimiter rateLimiterByGuava() {
		return com.google.common.util.concurrent.RateLimiter.create(1); // 1 requests per second
	}

//	@Bean
//	public io.github.resilience4j.ratelimiter.RateLimiter rateLimiter() {
//		RateLimiterConfig config = RateLimiterConfig.custom() //
//				.limitForPeriod(1) //
//				.limitRefreshPeriod(Duration.ofSeconds(1)) //
//				.timeoutDuration(Duration.ofMillis(0)) //
//				.build();
//
//		return io.github.resilience4j.ratelimiter.RateLimiter.of("myRateLimiter", config);
//	}

}

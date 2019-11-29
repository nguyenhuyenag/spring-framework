package com.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig<V, K> {

	@Value("${spring.redis.host}")
	private String host;

	@Value("${spring.redis.port}")
	private int port;

	@Bean
	public LettuceConnectionFactory redisConnectionFactory() {
		// Tạo Standalone Connection tới Redis
		return new LettuceConnectionFactory(new RedisStandaloneConfiguration(host, port));
	}

	@Bean
	@Primary
	public RedisTemplate<K, V> redisTemplate(RedisConnectionFactory redisFactory) {
		// tạo ra một RedisTemplate
		// Với Key là Object
		// Value là Object
		// RedisTemplate giúp chúng ta thao tác với Redis
		RedisTemplate<K, V> template = new RedisTemplate<>();
		template.setConnectionFactory(redisFactory);
		return template;
	}
}

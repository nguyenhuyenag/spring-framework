package com.kafka.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaTopicConfig {

	@Autowired
	private KafkaProperties kafkaProperties;

	@Bean
	public KafkaAdmin kafkaAdmin() {
		return new KafkaAdmin(kafkaProperties.buildAdminProperties());
	}

}

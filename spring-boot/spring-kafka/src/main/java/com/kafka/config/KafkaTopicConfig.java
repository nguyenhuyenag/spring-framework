package com.kafka.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaTopicConfig {
	
	private static final Logger LOG = LoggerFactory.getLogger(KafkaTopicConfig.class);

	@Autowired
	private KafkaProperties kafkaProperties;

	@Bean
	@ConditionalOnExpression("NOT ${spring.kafka.use-ssl}")
	public KafkaAdmin kafkaAdmin() {
		LOG.info("KafkaAdmin not SSL");
		return new KafkaAdmin(kafkaProperties.buildAdminProperties());
	}

}

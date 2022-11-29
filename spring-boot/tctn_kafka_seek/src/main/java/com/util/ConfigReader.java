package com.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:config.properties", encoding = "utf-8")
public class ConfigReader {
	
	public static String KAFKA_TOPIC_CONSUMER;

	@Value("${kafka.topic.consumer}")
	private void setJOB_TIME_RECALL(String topic) {
		ConfigReader.KAFKA_TOPIC_CONSUMER = topic;
	}
	
}

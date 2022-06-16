package com.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({ "classpath:kafka-config.properties", "classpath:config.properties" })
public class ConfigReader {

	public static long JOB_TIME_RECALL;
	public static String KAFKA_PRODUCER_TOPIC;
	public static String KAFKA_CONSUMER_TOPIC;

	@Value("${JOB_TIME_RECALL}")
	private void setJOB_TIME_RECALL(long time) {
		ConfigReader.JOB_TIME_RECALL = time;
	}
	
	@Value("${kafka.producer.topic}")
	private void setKAFKA_PRODUCER_TOPIC(String topic) {
		ConfigReader.KAFKA_PRODUCER_TOPIC = topic;
	}
	
	@Value("${kafka.consumer.topic}")
	private void setKAFKA_CONSUMER_TOPIC(String topic) {
		ConfigReader.KAFKA_CONSUMER_TOPIC = topic;
	}

}

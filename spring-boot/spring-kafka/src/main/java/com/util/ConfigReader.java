package com.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
//@PropertySource({ //
//	"classpath:kafka-config.properties" //
//})
@PropertySources({
	@PropertySource("classpath:config.properties"),
	@PropertySource("classpath:kafka-config.properties")
})
public class ConfigReader {

	public static String KAFKA_PRODUCER_TOPIC;
	public static String KAFKA_CONSUMER_TOPIC;

	@Value("${kafka.producer.topicName}")
	private void setKAFKA_PRODUCER_TOPIC(String topic) {
		ConfigReader.KAFKA_PRODUCER_TOPIC = topic;
	}

	@Value("${kafka.consumer.topicName}")
	private void setKAFKA_CONSUMER_TOPIC(String topic) {
		ConfigReader.KAFKA_CONSUMER_TOPIC = topic;
	}

}

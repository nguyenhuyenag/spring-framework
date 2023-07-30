package com.bakup;

import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;

public class MessageFilterListeners {

	// @Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> filterKafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(null); // <- ConsumerFactory
		factory.setRecordFilterStrategy(record -> record.value().contains("World"));
		return factory;
	}

}

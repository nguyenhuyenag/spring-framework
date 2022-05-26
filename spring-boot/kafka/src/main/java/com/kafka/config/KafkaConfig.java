package com.kafka.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@EnableKafka
@Configuration
public class KafkaConfig {

	private static final Logger LOG = LoggerFactory.getLogger(KafkaConfig.class);

	String SSL_PATH = "D:/ts24corp/workspace/ts24projects/hddt-tct-kafka/src/main/resources/ssl/";

	private Map<String, Object> kafkaConfig() {
		HashMap<String, Object> map = new HashMap<>();
		try {
			PropertiesFactoryBean factory = new PropertiesFactoryBean();
			factory.setLocation(new ClassPathResource("kafka-config.properties"));
			factory.afterPropertiesSet();
			factory.getObject().entrySet().forEach(e -> {
				map.put(String.valueOf(e.getKey()), e.getValue());
			});
			map.put(KafkaConstant.SSL_KEYSTORE_LOCATION, SSL_PATH + "client.keystore.jks");
			map.put(KafkaConstant.SSL_TRUSTSTORE_LOCATION, SSL_PATH + "client.truststore.jks");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}

	@Bean
	public ProducerFactory<String, Object> producerFactory() {
		Map<String, Object> config = new HashMap<>(kafkaConfig());
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		// config.put(JsonDeserializer.TRUSTED_PACKAGES, "com.kafka");
		// config.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, "20971520"); // kich thuoc message
		return new DefaultKafkaProducerFactory<>(config);
	}

	@Bean
	public KafkaTemplate<String, Object> kafkaTemplate() {
		LOG.info("Add Kafka config");
		return new KafkaTemplate<>(producerFactory());
	}

	@Bean
	public ConsumerFactory<String, Object> consumerFactory() {
		Map<String, Object> config = new HashMap<>(kafkaConfig());
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class); // or JsonDeserializer
		config.put(ConsumerConfig.GROUP_ID_CONFIG, KafkaConstant.CONSUMER_GROUP_ID);
		// config.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
		// config.put(JsonDeserializer.TRUSTED_PACKAGES, "com.kafka");
		// đọc các message của topic từ thời điểm hiển tại (default)
		// config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		return new DefaultKafkaConsumerFactory<>(config);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}

}

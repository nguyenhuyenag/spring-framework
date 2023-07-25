package com.kafka.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
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

	private final Map<String, Object> CONFIG = new HashMap<>();

	private Map<String, Object> kafkaConfig() {
		if (!CONFIG.isEmpty()) {
			return CONFIG;
		}
		String fileConfig = "kafka-config.properties";
		Resource resource = new ClassPathResource(fileConfig);
		if (!resource.exists()) {
			LOG.info("File '{}' not found!", fileConfig);
			return CONFIG;
		}
		try (InputStream inputStream = resource.getInputStream()) {
			Properties properties = new Properties();
			properties.load(inputStream);
			properties.forEach((key, value) -> CONFIG.put((String) key, value));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return CONFIG;
	}

	@Bean
	public KafkaTemplate<String, Object> kafkaTemplate(ProducerFactory<String, Object> factory) {
		return new KafkaTemplate<>(factory);
	}

	@Bean
	public ProducerFactory<String, Object> producerFactory() {
		Map<String, Object> producerConfig = new HashMap<>(kafkaConfig());
		producerConfig.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		producerConfig.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		return new DefaultKafkaProducerFactory<>(producerConfig);
	}

	@Bean
	public ConsumerFactory<String, Object> consumerFactory() {
		Map<String, Object> consumerConfig = new HashMap<>(kafkaConfig());
		consumerConfig.put(ConsumerConfig.GROUP_ID_CONFIG, KafkaConstant.CONSUMER_GROUP_ID);
		consumerConfig.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		// or JsonDeserializer
		consumerConfig.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		return new DefaultKafkaConsumerFactory<>(consumerConfig);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(
			ConsumerFactory<String, Object> factory) {
		ConcurrentKafkaListenerContainerFactory<String, String> listener = new ConcurrentKafkaListenerContainerFactory<>();
		listener.setConsumerFactory(factory);
		return listener;
	}

}

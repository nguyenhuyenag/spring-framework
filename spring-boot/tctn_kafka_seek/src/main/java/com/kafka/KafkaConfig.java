package com.kafka;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
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

import com.util.KafkaConstant;

@EnableKafka
@Configuration
public class KafkaConfig {
	
	private static final Logger LOG = LoggerFactory.getLogger(KafkaConfig.class);
	
	@Value("${kafka.use-ssl}")
	private boolean useSSL;
	
	private Map<String, Object> kafkaConfig() {
		HashMap<String, Object> map = new HashMap<>();
		try {
			PropertiesFactoryBean factory = new PropertiesFactoryBean();
			factory.setLocation(new ClassPathResource("kafka-config.properties"));
			factory.afterPropertiesSet();
			factory.getObject().entrySet().forEach(e -> {
				map.put(String.valueOf(e.getKey()), e.getValue());
			});
			if (useSSL) {
				Resource keystore = new ClassPathResource("ssl/client.keystore.jks");
				Resource truststore = new ClassPathResource("ssl/client.truststore.jks");
				if (keystore.exists() && truststore.exists()) {
					map.put(KafkaConstant.SSL_KEYSTORE_LOCATION, keystore.getFile().toString());
					map.put(KafkaConstant.SSL_TRUSTSTORE_LOCATION, truststore.getFile().toString());
				} else {
					LOG.error("File keystore or truststore not found!");
				}
			}
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
		return new DefaultKafkaProducerFactory<>(config);
	}

	@Bean
	public KafkaTemplate<String, Object> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}

	@Bean
	public ConsumerFactory<String, Object> consumerFactory() {
		Map<String, Object> config = new HashMap<>(kafkaConfig());
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class); // or JsonDeserializer
		config.put(ConsumerConfig.GROUP_ID_CONFIG, KafkaConstant.CONSUMER_GROUP_ID);
		return new DefaultKafkaConsumerFactory<>(config);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}

}

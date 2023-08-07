package com.kafka.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
@ConditionalOnProperty( //
	value = "spring.kafka.use-ssl", //
	havingValue = "true", //
	matchIfMissing = false //
)
public class KafkaSSLConfig {

	private static final Logger LOG = LoggerFactory.getLogger(KafkaSSLConfig.class);
	
	// @Autowired
	// private BeanFactory beanFactory;

	@Autowired
	private ProducerFactory<String, Object> producerFactory;

	@Autowired
	private ConsumerFactory<String, Object> consumerFactory;
	
	// private ApplicationContext applicationContext;

	public static Map<String, Object> SSL_CONFIG = new HashMap<>();

	public static Map<String, Object> loadSSLConfig() throws IOException {
		if (SSL_CONFIG.isEmpty()) {
			Resource resource = new ClassPathResource("client-ssl.properties");
			if (!resource.exists()) {
				LOG.info("File '{}' not found!", resource.getFile().getName());
			} else {
				try (InputStream inputStream = resource.getInputStream()) {
					Properties properties = new Properties();
					properties.load(inputStream);
					properties.forEach((key, value) -> SSL_CONFIG.put((String) key, value));
					// Update SSL file location
					Resource keystore = new ClassPathResource("/static/ssl/client.keystore.jks");
					Resource truststore = new ClassPathResource("/static/ssl/client.truststore.jks");
					if (keystore.exists() && truststore.exists()) {
						SSL_CONFIG.put("ssl.keystore.location", keystore.getFile().toString());
						SSL_CONFIG.put("ssl.truststore.location", truststore.getFile().toString());
					} else {
						LOG.error("File keystore or truststore not found!");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return SSL_CONFIG;
	}

	@Bean
	public void consumerSSL() throws IOException {
		consumerFactory.updateConfigs(loadSSLConfig());
	}

	@Bean
	public void producerSSL() throws IOException {
		producerFactory.updateConfigs(loadSSLConfig());
	}
	
//	@Bean
//	public KafkaAdmin kafkaAdminWithSSL() {
//////		DefaultSingletonBeanRegistry registry = (DefaultSingletonBeanRegistry) applicationContext.get
//////		registry.destroySingleton({yourbean}) //destroys the bean object
//////		registry.registerSingleton({yourbeanname}, {newbeanobject}) //add to singleton beans cache
////		if (applicationContext instanceof DefaultSingletonBeanRegistry) {
////            DefaultSingletonBeanRegistry singletonBeanRegistry = (DefaultSingletonBeanRegistry) applicationContext;
////         // Get a bean by its class.
////            KafkaAdmin myBean = applicationContext.getBean(KafkaAdmin.class);
////            String beanName = BeanFactoryUtils.beanNameForTypeIncludingAncestors(KafkaAdmin.class);
////            singletonBeanRegistry.destroySingleton(null);
////        }
//		return null;
//	}

}

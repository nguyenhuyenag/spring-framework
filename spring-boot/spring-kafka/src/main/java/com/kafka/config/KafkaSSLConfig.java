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

@Configuration
@ConditionalOnProperty(value = "spring.kafka.use-ssl", //
		havingValue = "true", //
		matchIfMissing = false //
)
public class KafkaSSLConfig {

	private static final Logger LOG = LoggerFactory.getLogger(KafkaSSLConfig.class);

	@Autowired
	private ConsumerFactory<String, Object> consumerFactory;

	public static Map<String, Object> loadSSLConfig() throws IOException {
		Map<String, Object> fileContent = new HashMap<>();
		Resource resource = new ClassPathResource("client-ssl.properties");
		if (!resource.exists()) {
			LOG.info("File '{}' not found!", resource.getFile().getName());
		} else {
			try (InputStream inputStream = resource.getInputStream()) {
				Properties properties = new Properties();
				properties.load(inputStream);
				properties.forEach((key, value) -> fileContent.put((String) key, value));
				// Update SSL file location
				Resource keystore = new ClassPathResource("/static/ssl/client.keystore.jks");
				Resource truststore = new ClassPathResource("/static/ssl/client.truststore.jks");
				if (keystore.exists() && truststore.exists()) {
					fileContent.put("ssl.keystore.location", keystore.getFile().toString());
					fileContent.put("ssl.truststore.location", truststore.getFile().toString());
				} else {
					LOG.error("File keystore or truststore not found!");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fileContent;
	}

	@Bean
	public void addSSLConsumer() throws IOException {
		// System.out.println("Testttttttttttttttttttttttttt: Add SSL Config");
		Map<String, Object> sslConfig = loadSSLConfig();
		// System.out.println(sslConfig);
		consumerFactory.updateConfigs(sslConfig);
	}

}

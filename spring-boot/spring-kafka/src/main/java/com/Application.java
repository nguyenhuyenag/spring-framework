
package com;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.util.KafkaUtils;

@EnableScheduling
@SpringBootApplication
public class Application extends SpringBootServletInitializer implements CommandLineRunner {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// KafkaUtils.deleteTopics("topicName2023");
		// KafkaUtils.createTopic("topic2022");
		KafkaUtils.showTopicsInfor();
		// KafkaUtils.countUnConsumerMessage();
	}

}

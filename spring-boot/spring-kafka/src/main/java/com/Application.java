
package com;

import java.util.List;

import org.apache.kafka.common.PartitionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.kafka.core.KafkaTemplate;
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
	
	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;
	
	@Autowired
	KafkaUtils kafkaUtils;
	
	public void test() {
		// String defaultTopic = kafkaTemplate.getDefaultTopic();
		// System.out.println(defaultTopic);
		List<PartitionInfo> partitionsFor = kafkaTemplate.partitionsFor("topic2023");
		for (PartitionInfo p : partitionsFor) {
			System.out.println("TopicName: " + p.topic());
			System.out.println("Partition: " + p.partition());
		}
	}

	@Override
	public void run(String... args) throws Exception {
		// KafkaUtils.deleteTopics("topic1021", "topic2023", "topic2021", "topic2022");
		// kafkaUtils.createTopic("topic2021", 3);
		// kafkaUtils.createTopic("topic2022", 2);
		// kafkaUtils.createTopic("topic2023", 5);
		// kafkaUtils.countUnConsumerMessage();
		// kafkaUtils.showTopicsInfor();
		kafkaUtils.isTopicExist("topic2023");
		// test();
	}

}

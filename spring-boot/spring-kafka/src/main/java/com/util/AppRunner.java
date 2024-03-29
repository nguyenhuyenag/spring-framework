package com.util;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner {

	@Autowired
	KafkaUtils kafkaUtils;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// createTopics();
		// kafkaUtils.isTopicExist("topic202");
		// kafkaUtils.topicInfo("test");
		kafkaUtils.showAllTopicsInfor();
		// kafkaUtils.test();
		
		// TODO
		// kafkaUtils.showKafkaConfig();
		// test();
		// kafkaUtils.countUnConsumerMessage(); // Error
		// System.out.println("Check running: " + kafkaUtils.isKafkaRunning());
		// kafkaUtils.deleteTopics("topic2021");
	}
	
	public void createTopics() throws InterruptedException, ExecutionException {
		// kafkaUtils.createTopic("topic2021", 1);
		// kafkaUtils.createTopic("topic2022", 2);
		// kafkaUtils.createTopic("topic2023", 3);
		// kafkaUtils.createTopic("topic2024", 4);
	}

}

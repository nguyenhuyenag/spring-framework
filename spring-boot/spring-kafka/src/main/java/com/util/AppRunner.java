package com.util;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.common.PartitionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner {

	@Autowired
	KafkaUtils kafkaUtils;

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	public void test() {
		// String defaultTopic = kafkaTemplate.getDefaultTopic();
		// System.out.println(defaultTopic);
		List<PartitionInfo> partitionsFor = kafkaTemplate.partitionsFor("topic2023");
		for (PartitionInfo p : partitionsFor) {
			System.out.println("TopicName: " + p.topic());
			System.out.println("Partition: " + p.partition());
		}
	}

	public void create() throws InterruptedException, ExecutionException {
		kafkaUtils.createTopic("topic2021", 3);
		kafkaUtils.createTopic("topic2022", 2);
		kafkaUtils.createTopic("topic2023", 5);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// kafkaUtils.showTopicsInfor();
		// kafkaUtils.partitionsFor("topic2023");
		// System.out.println("Check running: " + kafkaUtils.isBrokerRunning());
		// TODO
		// create();
		// kafkaUtils.deleteTopics("topic2021");
		// kafkaUtils.isTopicExist("topic2023");
		// kafkaUtils.showKafkaConfig();
		// test();
		// kafkaUtils.countUnConsumerMessage(); // Error
	}

}

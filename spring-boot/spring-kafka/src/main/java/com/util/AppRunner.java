package com.util;

import java.util.List;

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

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// kafkaUtils.deleteTopics("topic2021");
		// kafkaUtils.createTopic("topic2021", 3);
		// kafkaUtils.createTopic("topic2022", 2);
		// kafkaUtils.createTopic("topic2023", 5);
		// kafkaUtils.countUnConsumerMessage();
		// kafkaUtils.isTopicExist("topic2023");
		// kafkaUtils.showTopicsInfor();
		kafkaUtils.showTopicsInfor2();
		// kafkaUtils.showKafkaConfig();
		// test();
	}

}

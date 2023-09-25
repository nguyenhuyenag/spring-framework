package com.bakup;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

import com.kafka.config.KafkaConstant;
import com.service.KafkaService;

@Component
public class KafkaConsumer0 {

	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(KafkaConsumer0.class);
	
	@Autowired
	private KafkaService kafkaService;
	
	private void handleReceivedMessage(String message, int partition) {
		LOG.info("Received message: ThreadID={}, partition={}", Thread.currentThread().getId(), partition);
		// kafkaService.receivedMessage(message);
	}
	
	@KafkaListener(
		id = "id0", //
		autoStartup = "${kafka.consumer.autoStartup}", // 
		topicPartitions = {@TopicPartition(topic = "${kafka.consumer.topicName}", partitions = { "0" }) }, //
		groupId = KafkaConstant.CONSUMER_GROUP_ID, //
		containerFactory = KafkaConstant.KAFKA_LISTENER_CONTAINER_FACTORY //
	)
	public void receivedMessage0(String message) {
		handleReceivedMessage(message, 0);
	}
	
	@KafkaListener(
		id = "id1", //
		autoStartup = "${kafka.consumer.autoStartup}", // 
		topicPartitions = {@TopicPartition(topic = "${kafka.consumer.topicName}", partitions = { "1" }) }, //
		groupId = KafkaConstant.CONSUMER_GROUP_ID, //
		containerFactory = KafkaConstant.KAFKA_LISTENER_CONTAINER_FACTORY //
	)
	public void receivedMessage1(String message) {
		handleReceivedMessage(message, 1);
	}
	
	@KafkaListener(
		id = "id2", //
		autoStartup = "${kafka.consumer.autoStartup}", // 
		topicPartitions = {@TopicPartition(topic = "${kafka.consumer.topicName}", partitions = { "2" }) }, //
		groupId = KafkaConstant.CONSUMER_GROUP_ID, //
		containerFactory = KafkaConstant.KAFKA_LISTENER_CONTAINER_FACTORY //
	)
	public void receivedMessage2(String message) {
		handleReceivedMessage(message, 2);
	}

}

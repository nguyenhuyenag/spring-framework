package com.kafka.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.kafka.config.KafkaConstant;
import com.service.MessageService;

@Component
public class KafkaConsumer {

	@Autowired
	private MessageService messageService;

	@KafkaListener(
		autoStartup = "${kafka.auto.startup}", //
		topics = "${kafka.topic.consumer}",
		id = "id1",
		groupId = "group-id-1",
		containerFactory = KafkaConstant.KAFKA_LISTENER_CONTAINER_FACTORY
	)
	public void listenPartition1(String message) {
		System.out.println("Listener Id1, Thread ID: " + Thread.currentThread().getId());
		messageService.received(message);
	}
	
	@KafkaListener(
		autoStartup = "${kafka.auto.startup}", //
		topics = "${kafka.topic.consumer}",
		id = "id2",
		groupId = "group-id-1",
		containerFactory = KafkaConstant.KAFKA_LISTENER_CONTAINER_FACTORY
	)
	public void listenPartition2(String message) {
		System.out.println("Listener Id2, Thread ID: " + Thread.currentThread().getId());
		messageService.received(message);
	}

}

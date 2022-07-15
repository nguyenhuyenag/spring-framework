package com.kafka.consumer;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.kafka.config.KafkaConstant;
import com.service.DataService;

@Component
public class KafkaConsumer {

	// private static final Logger LOG = LoggerFactory.getLogger(KafkaConsumer.class);

	@Autowired
	private DataService dataService;

	private void handleMessage(String listener, String message) {
		dataService.receiveMessage(listener, message);
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@KafkaListener( // 
		id = "id0", //
		autoStartup = "${kafka.consumer.startup}", // 
		topicPartitions = { @TopicPartition(topic = "${kafka.consumer.topic}", partitions = { "0" }) }, //
		groupId = KafkaConstant.CONSUMER_GROUP_ID, //
		containerFactory = KafkaConstant.KAFKA_LISTENER_CONTAINER_FACTORY //
	)
	public void listenPartition0(@Payload String message) {
		// LOG.info("Listener Id0, Thread ID: {}", Thread.currentThread().getId());
		handleMessage("id0", message);
	}

	@KafkaListener(autoStartup = "${kafka.consumer.startup}", id = "id1", topicPartitions = {
			@TopicPartition(topic = "${kafka.consumer.topic}", partitions = { "1" }) }, //
			groupId = KafkaConstant.CONSUMER_GROUP_ID, containerFactory = KafkaConstant.KAFKA_LISTENER_CONTAINER_FACTORY)
	public void listenPartition1(@Payload String message) {
		// LOG.info("Listener Id1, Thread ID: {}", Thread.currentThread().getId());
		handleMessage("id1", message);
	}

	@KafkaListener(autoStartup = "${kafka.consumer.startup}", id = "id2", topicPartitions = {
			@TopicPartition(topic = "${kafka.consumer.topic}", partitions = { "2" }) }, //
			groupId = KafkaConstant.CONSUMER_GROUP_ID, containerFactory = KafkaConstant.KAFKA_LISTENER_CONTAINER_FACTORY)
	public void listenPartition2(@Payload String message) {
		// LOG.info("Listener Id2, Thread ID: {}", Thread.currentThread().getId());
		handleMessage("id2", message);
	}

	@KafkaListener(autoStartup = "${kafka.consumer.startup}", id = "id3", topicPartitions = {
			@TopicPartition(topic = "${kafka.consumer.topic}", partitions = { "3" }) }, //
			groupId = KafkaConstant.CONSUMER_GROUP_ID, containerFactory = KafkaConstant.KAFKA_LISTENER_CONTAINER_FACTORY)
	public void listenPartition3(@Payload String message) {
		// LOG.info("Listener Id3, Thread ID: {}", Thread.currentThread().getId());
		handleMessage("id3", message);
	}

	@KafkaListener(autoStartup = "${kafka.consumer.startup}", id = "id4", topicPartitions = {
			@TopicPartition(topic = "${kafka.consumer.topic}", partitions = { "4" }) }, //
			groupId = KafkaConstant.CONSUMER_GROUP_ID, containerFactory = KafkaConstant.KAFKA_LISTENER_CONTAINER_FACTORY)
	public void listenPartition4(@Payload String message) {
		// LOG.info("Listener Id4, Thread ID: {}", Thread.currentThread().getId());
		handleMessage("id4", message);
	}

	@KafkaListener(autoStartup = "${kafka.consumer.startup}", id = "id5", topicPartitions = {
			@TopicPartition(topic = "${kafka.consumer.topic}", partitions = { "5" }) }, //
			groupId = KafkaConstant.CONSUMER_GROUP_ID, containerFactory = KafkaConstant.KAFKA_LISTENER_CONTAINER_FACTORY)
	public void listenPartition5(@Payload String message) {
		// LOG.info("Listener Id5, Thread ID: {}", Thread.currentThread().getId());
		handleMessage("id5", message);
	}

	@KafkaListener(autoStartup = "${kafka.consumer.startup}", id = "id6", topicPartitions = {
			@TopicPartition(topic = "${kafka.consumer.topic}", partitions = { "6" }) }, //
			groupId = KafkaConstant.CONSUMER_GROUP_ID, containerFactory = KafkaConstant.KAFKA_LISTENER_CONTAINER_FACTORY)
	public void listenPartition6(@Payload String message) {
		// LOG.info("Listener Id6, Thread ID: {}", Thread.currentThread().getId());
		handleMessage("id6", message);
	}

	@KafkaListener(autoStartup = "${kafka.consumer.startup}", id = "id7", topicPartitions = {
			@TopicPartition(topic = "${kafka.consumer.topic}", partitions = { "7" }) }, //
			groupId = KafkaConstant.CONSUMER_GROUP_ID, containerFactory = KafkaConstant.KAFKA_LISTENER_CONTAINER_FACTORY)
	public void listenPartition7(@Payload String message) {
		// LOG.info("Listener Id7, Thread ID: {}", Thread.currentThread().getId());
		handleMessage("id7", message);
	}

	@KafkaListener(autoStartup = "${kafka.consumer.startup}", id = "id8", topicPartitions = {
			@TopicPartition(topic = "${kafka.consumer.topic}", partitions = { "8" }) }, //
			groupId = KafkaConstant.CONSUMER_GROUP_ID, containerFactory = KafkaConstant.KAFKA_LISTENER_CONTAINER_FACTORY)
	public void listenPartition8(@Payload String message) {
		// LOG.info("Listener Id8, Thread ID: {}", Thread.currentThread().getId());
		handleMessage("id8", message);
	}

	@KafkaListener(autoStartup = "${kafka.consumer.startup}", id = "id9", topicPartitions = {
			@TopicPartition(topic = "${kafka.consumer.topic}", partitions = { "9" }) }, //
			groupId = KafkaConstant.CONSUMER_GROUP_ID, containerFactory = KafkaConstant.KAFKA_LISTENER_CONTAINER_FACTORY)
	public void listenPartition9(@Payload String message) {
		// LOG.info("Listener Id9, Thread ID: {}", Thread.currentThread().getId());
		handleMessage("id9", message);
	}

	@KafkaListener(autoStartup = "${kafka.consumer.startup}", id = "id10", topicPartitions = {
			@TopicPartition(topic = "${kafka.consumer.topic}", partitions = { "10" }) }, //
			groupId = KafkaConstant.CONSUMER_GROUP_ID, containerFactory = KafkaConstant.KAFKA_LISTENER_CONTAINER_FACTORY)
	public void listenPartition10(@Payload String message) {
		// LOG.info("Listener Id10, Thread ID: {}", Thread.currentThread().getId());
		handleMessage("id10", message);
	}

	@KafkaListener(autoStartup = "${kafka.consumer.startup}", id = "id11", topicPartitions = {
			@TopicPartition(topic = "${kafka.consumer.topic}", partitions = { "11" }) }, //
			groupId = KafkaConstant.CONSUMER_GROUP_ID, containerFactory = KafkaConstant.KAFKA_LISTENER_CONTAINER_FACTORY)
	public void listenPartition11(@Payload String message) {
		// LOG.info("Listener Id11, Thread ID: {}", Thread.currentThread().getId());
		handleMessage("id11", message);
	}

}
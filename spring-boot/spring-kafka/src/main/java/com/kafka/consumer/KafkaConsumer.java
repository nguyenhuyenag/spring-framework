package com.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

import com.kafka.config.KafkaConstant;

@Component
public class KafkaConsumer {

	private void handleMessage(String message) {
		System.out.println(message);
	}

	@KafkaListener(autoStartup = "${kafka.consumer.startup}", id = "id0", topicPartitions = {
			@TopicPartition(topic = "${kafka.consumer.topic}", partitions = { "0" }) }, //
			groupId = KafkaConstant.CONSUMER_GROUP_ID, containerFactory = KafkaConstant.KAFKA_LISTENER_CONTAINER_FACTORY)
	public void listenPartition0(String message) {
		System.out.println("Listener Id0, Thread ID: " + Thread.currentThread().getId());
		handleMessage(message);
	}

	@KafkaListener(autoStartup = "${kafka.consumer.startup}", id = "id1", topicPartitions = {
			@TopicPartition(topic = "${kafka.consumer.topic}", partitions = { "1" }) }, //
			groupId = KafkaConstant.CONSUMER_GROUP_ID, containerFactory = KafkaConstant.KAFKA_LISTENER_CONTAINER_FACTORY)
	public void listenPartition1(String message) {
		System.out.println("Listener Id1, Thread ID: " + Thread.currentThread().getId());
		handleMessage(message);
	}

	@KafkaListener(autoStartup = "${kafka.consumer.startup}", id = "id2", topicPartitions = {
			@TopicPartition(topic = "${kafka.consumer.topic}", partitions = { "2" }) }, //
			groupId = KafkaConstant.CONSUMER_GROUP_ID, containerFactory = KafkaConstant.KAFKA_LISTENER_CONTAINER_FACTORY)
	public void listenPartition2(String message) {
		System.out.println("Listener Id2, Thread ID: " + Thread.currentThread().getId());
		handleMessage(message);
	}

	@KafkaListener(autoStartup = "${kafka.consumer.startup}", id = "id3", topicPartitions = {
			@TopicPartition(topic = "${kafka.consumer.topic}", partitions = { "3" }) }, //
			groupId = KafkaConstant.CONSUMER_GROUP_ID, containerFactory = KafkaConstant.KAFKA_LISTENER_CONTAINER_FACTORY)
	public void listenPartition3(String message) {
		System.out.println("Listener Id3, Thread ID: " + Thread.currentThread().getId());
		handleMessage(message);
	}

	@KafkaListener(autoStartup = "${kafka.consumer.startup}", id = "id4", topicPartitions = {
			@TopicPartition(topic = "${kafka.consumer.topic}", partitions = { "4" }) }, //
			groupId = KafkaConstant.CONSUMER_GROUP_ID, containerFactory = KafkaConstant.KAFKA_LISTENER_CONTAINER_FACTORY)
	public void listenPartition4(String message) {
		System.out.println("Listener Id4, Thread ID: " + Thread.currentThread().getId());
		handleMessage(message);
	}

	@KafkaListener(autoStartup = "${kafka.consumer.startup}", id = "id5", topicPartitions = {
			@TopicPartition(topic = "${kafka.consumer.topic}", partitions = { "5" }) }, //
			groupId = KafkaConstant.CONSUMER_GROUP_ID, containerFactory = KafkaConstant.KAFKA_LISTENER_CONTAINER_FACTORY)
	public void listenPartition5(String message) {
		System.out.println("Listener Id5, Thread ID: " + Thread.currentThread().getId());
		handleMessage(message);
	}

	@KafkaListener(autoStartup = "${kafka.consumer.startup}", id = "id6", topicPartitions = {
			@TopicPartition(topic = "${kafka.consumer.topic}", partitions = { "6" }) }, //
			groupId = KafkaConstant.CONSUMER_GROUP_ID, containerFactory = KafkaConstant.KAFKA_LISTENER_CONTAINER_FACTORY)
	public void listenPartition6(String message) {
		System.out.println("Listener Id6, Thread ID: " + Thread.currentThread().getId());
		handleMessage(message);
	}

	@KafkaListener(autoStartup = "${kafka.consumer.startup}", id = "id7", topicPartitions = {
			@TopicPartition(topic = "${kafka.consumer.topic}", partitions = { "7" }) }, //
			groupId = KafkaConstant.CONSUMER_GROUP_ID, containerFactory = KafkaConstant.KAFKA_LISTENER_CONTAINER_FACTORY)
	public void listenPartition7(String message) {
		System.out.println("Listener Id7, Thread ID: " + Thread.currentThread().getId());
		handleMessage(message);
	}

	@KafkaListener(autoStartup = "${kafka.consumer.startup}", id = "id8", topicPartitions = {
			@TopicPartition(topic = "${kafka.consumer.topic}", partitions = { "8" }) }, //
			groupId = KafkaConstant.CONSUMER_GROUP_ID, containerFactory = KafkaConstant.KAFKA_LISTENER_CONTAINER_FACTORY)
	public void listenPartition8(String message) {
		System.out.println("Listener Id8, Thread ID: " + Thread.currentThread().getId());
		handleMessage(message);
	}

	@KafkaListener(autoStartup = "${kafka.consumer.startup}", id = "id9", topicPartitions = {
			@TopicPartition(topic = "${kafka.consumer.topic}", partitions = { "9" }) }, //
			groupId = KafkaConstant.CONSUMER_GROUP_ID, containerFactory = KafkaConstant.KAFKA_LISTENER_CONTAINER_FACTORY)
	public void listenPartition9(String message) {
		System.out.println("Listener Id9, Thread ID: " + Thread.currentThread().getId());
		handleMessage(message);
	}

	@KafkaListener(autoStartup = "${kafka.consumer.startup}", id = "id10", topicPartitions = {
			@TopicPartition(topic = "${kafka.consumer.topic}", partitions = { "10" }) }, //
			groupId = KafkaConstant.CONSUMER_GROUP_ID, containerFactory = KafkaConstant.KAFKA_LISTENER_CONTAINER_FACTORY)
	public void listenPartition10(String message) {
		System.out.println("Listener Id10, Thread ID: " + Thread.currentThread().getId());
		handleMessage(message);
	}

	@KafkaListener(autoStartup = "${kafka.consumer.startup}", id = "id11", topicPartitions = {
			@TopicPartition(topic = "${kafka.consumer.topic}", partitions = { "11" }) }, //
			groupId = KafkaConstant.CONSUMER_GROUP_ID, containerFactory = KafkaConstant.KAFKA_LISTENER_CONTAINER_FACTORY)
	public void listenPartition11(String message) {
		System.out.println("Listener Id11, Thread ID: " + Thread.currentThread().getId());
		handleMessage(message);
	}

}
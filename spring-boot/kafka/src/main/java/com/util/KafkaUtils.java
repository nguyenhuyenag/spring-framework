package com.util;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.DeleteTopicsResult;
import org.apache.kafka.clients.admin.ListTopicsOptions;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.stereotype.Component;

@Component
public class KafkaUtils {
	
	private static final Logger LOG = LoggerFactory.getLogger(KafkaUtils.class);

	private static final int ADMIN_CLIENT_TIMEOUT_MS = 5000;

	public static ConsumerFactory<?, ?> consumerFactory() {
		return SpringUtils.CTX.getBean(ConsumerFactory.class);
	}

	public static Map<String, Object> config() {
		return consumerFactory().getConfigurationProperties();
	}

	public void start() {
		String path = "cmd /c start d:\\sample\\sample.bat";
		Runtime rn = Runtime.getRuntime();
		try {
			Process pr = rn.exec(path);
			pr.isAlive();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteTopicMessage() {
		// ConfigReader.KAFKA_PRODUCER_TOPIC;
	}

	public static boolean isBrokerRunning() {
		try (AdminClient client = AdminClient.create(config())) {
			client.listTopics(new ListTopicsOptions().timeoutMs(ADMIN_CLIENT_TIMEOUT_MS)).listings().get();
			return true;
		} catch (InterruptedException | ExecutionException ex) {
			LOG.error("Kafka is not available, timed out after {} ms", ADMIN_CLIENT_TIMEOUT_MS);
		}
		return false;
	}

	public static void showTopics() {
		try (AdminClient adminClient = AdminClient.create(config())) {
			ListTopicsOptions listTopicsOptions = new ListTopicsOptions();
			listTopicsOptions.listInternal(true);
			System.out.println(adminClient.listTopics(listTopicsOptions).names().get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

	public static void deleteTopics(List<String> kafkaTopics) {
		try (AdminClient adminClient = AdminClient.create(config())) {
			DeleteTopicsResult deleteTopicsResult = adminClient.deleteTopics(kafkaTopics);
			while (!deleteTopicsResult.all().isDone()) {
				// Wait for future task to complete
			}
		}
	}

	public static void createTopic(String topicname) {
		try (AdminClient adminClient = AdminClient.create(config())) {
			// new NewTopic(topicName, numPartitions, replicationFactor)
			NewTopic newTopic = new NewTopic(topicname, 12, (short) 1);
			List<NewTopic> newTopics = Arrays.asList(newTopic);
			adminClient.createTopics(newTopics);
		}
	}

	public static void showTopicsInfor() {
		try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(config())) {
			Map<String, List<PartitionInfo>> topics = consumer.listTopics();
			if (topics.isEmpty()) {
				System.out.println("No topics!");
				return;
			}
			for (Map.Entry<String, List<PartitionInfo>> entry : topics.entrySet()) {
				// "Key = " + entry.getKey() + ", Value = " + entry.getValue();
				System.out.println("Topic name: " + entry.getKey() + ", Partions: "
						+ Integer.toString(entry.getValue().size()) + "\n");
			}
		}
	}

}
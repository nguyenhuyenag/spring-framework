package com.util;

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
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.stereotype.Component;

@Component
public class KafkaUtils {

	public static ConsumerFactory<?, ?> consumerFactory() {
		return SpringUtils.getBean(ConsumerFactory.class);
	}

	public static Map<String, Object> config() {
		return consumerFactory().getConfigurationProperties();
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
			for (Map.Entry<String, List<PartitionInfo>> entry : topics.entrySet()) {
				// "Key = " + entry.getKey() + ", Value = " + entry.getValue();
				System.out.println("Topic name: " + entry.getKey() + ", Partions: "
						+ Integer.toString(entry.getValue().size()) + "\n");
			}
		}
	}

}

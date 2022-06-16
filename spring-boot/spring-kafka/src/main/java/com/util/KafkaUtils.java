package com.util;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ConsumerGroupDescription;
import org.apache.kafka.clients.admin.DeleteTopicsResult;
import org.apache.kafka.clients.admin.ListTopicsOptions;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.ConsumerFactory;

// @Component
public class KafkaUtils {
	
	private static final Logger LOG = LoggerFactory.getLogger(KafkaUtils.class);

	private static final int ADMIN_CLIENT_TIMEOUT_MS = 5000;

	public static ConsumerFactory<?, ?> consumerFactory() {
		return SpringUtils.getBean(ConsumerFactory.class);
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
				System.out.println("Topic: " + entry.getKey() + ", Partions: "
						+ Integer.toString(entry.getValue().size()) + "\n");
			}
		}
	}
	
	public static void toDo() {
		try (AdminClient kafkaClient = AdminClient.create(config())) {
			// Here you get all the consumer groups
			List<String> groupIds = kafkaClient.listConsumerGroups().all().get().
			                       stream().map(s -> s.groupId()).collect(Collectors.toList()); 

			// Here you get all the descriptions for the groups
			Map<String, ConsumerGroupDescription> groups = kafkaClient.
			                                               describeConsumerGroups(groupIds).all().get();
			for (final String groupId : groupIds) {
			    ConsumerGroupDescription descr = groups.get(groupId);
			    //find if any description is connected to the topic with topicName
			    Optional<TopicPartition> tp = descr.members().stream().
			                                  map(s -> s.assignment().topicPartitions()).
			                                  flatMap(coll -> coll.stream()).
			                                  filter(s -> s.topic().equals(ConfigReader.KAFKA_CONSUMER_TOPIC)).findAny();
			            if (tp.isPresent()) {
			                //you found the consumer, so collect the group id somewhere
			            }
			} 
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

}

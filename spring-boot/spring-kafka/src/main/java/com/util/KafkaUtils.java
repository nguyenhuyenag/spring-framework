package com.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.DeleteTopicsResult;
import org.apache.kafka.clients.admin.DescribeTopicsResult;
import org.apache.kafka.clients.admin.ListTopicsOptions;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.admin.TopicDescription;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.bakup.SpringUtils;

@Component
public class KafkaUtils {

	private static final Logger LOG = LoggerFactory.getLogger(KafkaUtils.class);

	// @Autowired
	// private ConsumerFactory<?, ?> consumerFactory;

	@Value("${bootstrap.servers}")
	private String bootstrapServers;

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	private static final int ADMIN_CLIENT_TIMEOUT_MS = 5 * 1000;

	private KafkaUtils() {

	}

	private static Map<String, Object> config() {
		ConsumerFactory<?, ?> consumerFactory = SpringUtils.getBean(ConsumerFactory.class);
		return consumerFactory.getConfigurationProperties();
	}

	public static boolean isBrokerRunning() {
		try (AdminClient client = AdminClient.create(config())) {
			client.listTopics(new ListTopicsOptions().timeoutMs(ADMIN_CLIENT_TIMEOUT_MS)) //
					.listings().get();
			return true;
		} catch (InterruptedException | ExecutionException ex) {
			LOG.error("Kafka is not available, timed out after {} ms", ADMIN_CLIENT_TIMEOUT_MS);
		}
		return false;
	}

	public static void deleteTopics(String... kafkaTopics) {
		try (AdminClient adminClient = AdminClient.create(config())) {
			DeleteTopicsResult delete = adminClient.deleteTopics(Arrays.asList(kafkaTopics));
			while (!delete.all().isDone()) {
				// Wait for future task to complete
			}
			LOG.info("Delete topics completed");
		}
	}

	public void createTopic(String topicName, int numPartitions) throws InterruptedException, ExecutionException {
		if (isTopicExist(topicName)) {
			System.out.println("Topic '" + topicName + "' already exist!");
			return;
		}
		Properties props = new Properties();
		props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		try (AdminClient adminClient = AdminClient.create(props)) {
			NewTopic newTopic = new NewTopic(topicName, numPartitions, (short) 1);
			List<NewTopic> newTopics = Arrays.asList(newTopic);
			CreateTopicsResult createTopics = adminClient.createTopics(newTopics);
			// Wait for the creation to complete
			KafkaFuture<Void> allCreationFutures = createTopics.all();
			allCreationFutures.get(); // This will block until the creation is complete
			List<PartitionInfo> partitionsFor = kafkaTemplate.partitionsFor(topicName);
			for (PartitionInfo p : partitionsFor) {
				System.out.println("TopicName: " + p.topic());
				System.out.println("Partition: " + p.partition());
			}
		}
	}

	public void showTopicsInfor() {
		try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(config())) {
			Map<String, List<PartitionInfo>> topics = consumer.listTopics();
			if (topics.isEmpty()) {
				System.out.println("No topics!");
				return;
			}
			for (Entry<String, List<PartitionInfo>> entry : topics.entrySet()) {
				if (!"__consumer_offsets".equalsIgnoreCase(entry.getKey())) {
					System.out.println("Topic: " + entry.getKey() + ", partions: " + entry.getValue().size());
				}
			}
		}
	}

	public boolean isTopicExist(String topicName) throws InterruptedException, ExecutionException {
		try (AdminClient adminClient = AdminClient.create(config())) {
			// Prepare the list of topics to describe
			DescribeTopicsResult describeTopicsResult = adminClient.describeTopics(Collections.singleton(topicName));
			// Check if the topic exists
			TopicDescription topicDescription = describeTopicsResult.all().get().get(topicName);
			
			// Map<String, TopicDescription> map = describeTopicsResult.all().get();
			
			ListTopicsOptions options = new ListTopicsOptions();
		    options.listInternal(true); // includes internal topics such as __consumer_offsets
		    ListTopicsResult topics = adminClient.listTopics(options);
		    Set<String> currentTopicList = topics.names().get();
		    System.out.println("isTopicExist: " + currentTopicList);
		    
			return topicDescription != null;
		}
	}
	
	public static Set<TopicPartition> listTopicPartition() {
		ConsumerFactory<?, ?> consumerFactory = SpringUtils.getBean(ConsumerFactory.class);
		Consumer<?, ?> consumer = consumerFactory.createConsumer();
		Set<TopicPartition> partitions = new HashSet<>();
		List<PartitionInfo> listPInfo = consumer.partitionsFor(ConfigReader.KAFKA_CONSUMER_TOPIC);
		for (PartitionInfo pif : listPInfo) {
			partitions.add(new TopicPartition(pif.topic(), pif.partition()));
		}
		return partitions;
	}

	// private static Consumer<?, ?> createConsumer() {
//		ConsumerFactory<?, ?> consumerFactory = SpringUtils.getBean(ConsumerFactory.class);
//		return consumerFactory.createConsumer();
//	}

	static long total = 0;

	/**
	 * https://stackoverflow.com/a/58545511/10910098
	 */
	public void countUnConsumerMessage() {
		// long total = 0;
		try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(config())) {
			Set<TopicPartition> partitions = KafkaUtils.listTopicPartition();
			Map<TopicPartition, Long> endOffsets = consumer.endOffsets(partitions);
			Map<TopicPartition, OffsetAndMetadata> committed = consumer.committed(partitions);
			endOffsets.forEach((k, v) -> {
				if (v != null) {
					long currentOffset = committed.get(k).offset(); // Caused by: java.lang.NullPointerException: null
					long endoffset = v; // endoffset
					System.out.println("currentOffset: " + currentOffset + ", endoffset: " + endoffset);
					total += (endoffset - currentOffset);
				} else {
					System.out.println("Partition=" + k.partition() + ", OffsetAndMetadata is null");
				}
			});
		}
		System.out.println("Total (lag): " + total);
		total = 0;
	}

	// public static void showTopics() {
	// try (AdminClient adminClient = AdminClient.create(config())) {
	// ListTopicsOptions listTopicsOptions = new ListTopicsOptions();
	// listTopicsOptions.listInternal(true);
	// System.out.println(adminClient.listTopics(listTopicsOptions).names().get());
	// } catch (InterruptedException | ExecutionException e) {
	// e.printStackTrace();
	// }
	// }

	// public void start() {
	// String path = "cmd /c start d:\\sample\\sample.bat";
	// Runtime rn = Runtime.getRuntime();
	// try {
	// Process pr = rn.exec(path);
	// pr.isAlive();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }

}

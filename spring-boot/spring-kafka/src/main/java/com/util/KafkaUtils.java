package com.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.DeleteTopicsResult;
import org.apache.kafka.clients.admin.ListTopicsOptions;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.ConsumerFactory;

// @Component
public class KafkaUtils {

	private static final Logger LOG = LoggerFactory.getLogger(KafkaUtils.class);

	// @Autowired
	// private ConsumerFactory<?, ?> consumerFactory;

	private static final int ADMIN_CLIENT_TIMEOUT_MS = 5000;

	private KafkaUtils() {

	}

	private static Map<String, Object> config() {
		ConsumerFactory<?, ?> consumer = SpringUtils.getBean(ConsumerFactory.class);
		return consumer.getConfigurationProperties();
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

	public static void createTopic(String topicname) {
		try (AdminClient adminClient = AdminClient.create(config())) {
			NewTopic newTopic = new NewTopic(topicname, 12, (short) 1);
			List<NewTopic> newTopics = Arrays.asList(newTopic);
			adminClient.createTopics(newTopics);
			LOG.info("Create topic={}", topicname);
		}
	}

	public static void showTopicsInfor() {
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

	public static Set<TopicPartition> listTopicPartition() {
		ConsumerFactory<?, ?> consumerFactory = SpringUtils.getBean(ConsumerFactory.class);
		Consumer<?, ?> consumer = consumerFactory.createConsumer();
		Set<TopicPartition> partitions = new HashSet<>();
		for (PartitionInfo pif : consumer.partitionsFor(ConfigReader.KAFKA_CONSUMER_TOPIC)) {
			partitions.add(new TopicPartition(pif.topic(), pif.partition()));
		}
		return partitions;
	}

	public static Consumer<?, ?> createConsumer() {
		ConsumerFactory<?, ?> consumerFactory = SpringUtils.getBean(ConsumerFactory.class);
		return consumerFactory.createConsumer();
	}

	/**
	 * https://stackoverflow.com/a/58545511/10910098
	 */
	public static void countUnConsumer() {
		long total = 0;
		try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(config())) {
			Set<TopicPartition> partitions = KafkaUtils.listTopicPartition();
			Map<TopicPartition, Long> offsets = consumer.endOffsets(partitions);
			Map<TopicPartition, OffsetAndMetadata> map = consumer.committed(partitions);
			for (TopicPartition tp : partitions) {
				OffsetAndMetadata commitOffset = map.get(tp);
				long lag = commitOffset == null ? offsets.get(tp) : offsets.get(tp) - commitOffset.offset();
				total += lag;
			}
		}
		System.out.println("Lag: " + total);
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

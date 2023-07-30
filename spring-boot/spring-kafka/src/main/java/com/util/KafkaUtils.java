package com.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.DeleteTopicsResult;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.ListTopicsOptions;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaUtils {

	private static final Logger LOG = LoggerFactory.getLogger(KafkaUtils.class);

//	@Value("${bootstrap.servers}")
//	private String bootstrapServers;

	@Autowired
	private KafkaAdmin kafkaAdmin;

	// @Autowired
	// private KafkaProperties kafkaProperties;

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	@Autowired
	private ConsumerFactory<String, Object> consumerFactory;

	public void showAllTopicsInfor() {
		// Or 'new KafkaConsumer<>(kafkaProperties.buildConsumerProperties())'
		try (Consumer<String, Object> consumer = consumerFactory.createConsumer()) {
			// Sort by topic name
			Map<String, List<PartitionInfo>> topics = new TreeMap<>(consumer.listTopics());
			if (!topics.isEmpty()) {
				topics.forEach((key, value) -> {
					System.out.println("Topic=" + key + ", number of partions=" + value.size());
				});
			} else {
				System.out.println("No topics!");
			}
		}
	}
	
	public void topicInfo(String topicName) {
		partitionsForTopic(topicName);
		topicPartition(topicName);
	}

	private void partitionsForTopic(String topicName) {
		List<Integer> partitions = new ArrayList<>();
		List<PartitionInfo> topicInfo = kafkaTemplate.partitionsFor(topicName);
		for (PartitionInfo p : topicInfo) {
			partitions.add(p.partition());
		}
		System.out.printf("Topic=%s, partitions=%s\n", topicName, partitions);
	}

	public List<TopicPartition> topicPartition(String topicName) {
		try (Consumer<String, Object> consumer = consumerFactory.createConsumer()) {
			List<TopicPartition> partitions = new ArrayList<>();
			List<PartitionInfo> listInfo = consumer.partitionsFor(topicName);
			for (PartitionInfo p : listInfo) {
				partitions.add(new TopicPartition(p.topic(), p.partition()));
			}
			System.out.println("TopicPartition: " + partitions);
			return partitions;
		}
	}

	public boolean isTopicExist(String topicName) throws InterruptedException, ExecutionException {
		try (AdminClient adminClient = AdminClient.create(kafkaAdmin.getConfigurationProperties())) {
			ListTopicsOptions options = new ListTopicsOptions();
			options.listInternal(true); // Includes internal topics such as __consumer_offsets
			ListTopicsResult topics = adminClient.listTopics(options);
			Set<String> currentTopicList = topics.names().get();
			boolean contains = currentTopicList.contains(topicName);
			System.out.println("Check exist: " + contains);
			return contains;
		}
	}

	public void createTopic(String topicName, int numPartitions) throws InterruptedException, ExecutionException {
		if (!isTopicExist(topicName)) {
			try (AdminClient adminClient = AdminClient.create(kafkaAdmin.getConfigurationProperties())) {
				NewTopic newTopic = new NewTopic(topicName, numPartitions, (short) 1);
				List<NewTopic> newTopics = Arrays.asList(newTopic);
				CreateTopicsResult createTopics = adminClient.createTopics(newTopics);
				// Wait for the creation to complete
				KafkaFuture<Void> allCreationFutures = createTopics.all();
				allCreationFutures.get(); // This will block until the creation is complete
			}
		} else {
			System.out.println("Topic '" + topicName + "' already exist!");
		}
	}

	// TODO: In process

//	public void showKafkaConfig() {
//	System.out.println("Testttt: " + kafkaProperties.getTemplate());
//	System.out.println("Bootstrap servers: " + kafkaProperties.getBootstrapServers());
//	System.out.println("Default topic: " + kafkaProperties.getTemplate().getDefaultTopic());
//}

//private Map<String, Object> config() {
//	// ConsumerFactory<?, ?> consumerFactory = SpringUtils.getBean(ConsumerFactory.class);
//	// kafkaAdmin.getConfigurationProperties()
//	return consumerFactory.getConfigurationProperties();
//}

//	public void showTopicsInfor2() throws InterruptedException, ExecutionException {
//		try (AdminClient adminClient = AdminClient.create(kafkaAdmin.getConfigurationProperties())) {
//			DescribeTopicsResult describeTopicsResult = adminClient
//					.describeTopics(adminClient.listTopics().names().get());
//			Map<String, TopicDescription> topicDescriptionMap = describeTopicsResult.all().get();
//
//			Map<String, List<TopicPartitionInfo>> collect = topicDescriptionMap.entrySet().stream()
//					.collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().partitions()));
//			System.out.println(collect.toString());
//		}
//	}

	static long total = 0;

	/**
	 * https://stackoverflow.com/a/58545511/10910098
	 */
//	public void countUnConsumerMessage() {
//		// long total = 0;
//		try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(kafkaAdmin.getConfigurationProperties())) {
//			Set<TopicPartition> partitions = listTopicPartition();
//			Map<TopicPartition, Long> endOffsets = consumer.endOffsets(partitions);
//			Map<TopicPartition, OffsetAndMetadata> committed = consumer.committed(partitions);
//			endOffsets.forEach((k, v) -> {
//				if (v != null) {
//					long currentOffset = committed.get(k).offset(); // Caused by: java.lang.NullPointerException: null
//					long endoffset = v; // endoffset
//					System.out.println("currentOffset: " + currentOffset + ", endoffset: " + endoffset);
//					total += (endoffset - currentOffset);
//				} else {
//					System.out.println("Partition=" + k.partition() + ", OffsetAndMetadata is null");
//				}
//			});
//		}
//		System.out.println("Total (lag): " + total);
//		total = 0;
//	}

//	public boolean isKafkaRunning() {
//		final int ADMIN_CLIENT_TIMEOUT_MS = 5 * 1000;
//		// AdminClient adminClient = KafkaAdminClient.create(props);
//		try (AdminClient adminClient = AdminClient.create(kafkaAdmin.getConfigurationProperties())) {
//			ListTopicsOptions options = new ListTopicsOptions();
//			options.timeoutMs(ADMIN_CLIENT_TIMEOUT_MS);
//			KafkaFuture<Collection<TopicListing>> listings = adminClient.listTopics(options).listings();
//			listings.isDone();
//			Collection<TopicListing> collection = adminClient.listTopics(options).listings().get();
//			// adminClient.listTopics(options).names().get();
//			return true;
//		} catch (InterruptedException | ExecutionException ex) {
//			LOG.error("Kafka is not available, timed out after {} ms", ADMIN_CLIENT_TIMEOUT_MS);
//		}
//		return false;
//	}

	// TODO: Lỗi khi xóa topic
	public void deleteTopics(String... kafkaTopics) throws InterruptedException {
		Map<String, Object> configuration = new HashMap<>(kafkaAdmin.getConfigurationProperties());
		configuration.put("delete.topic.enable", true);
		try (AdminClient adminClient = KafkaAdminClient.create(configuration)) {
			DeleteTopicsResult delete = adminClient.deleteTopics(Arrays.asList(kafkaTopics));
			while (!delete.all().isDone()) {
				System.out.println("Wait for future task to complete!");
				TimeUnit.SECONDS.sleep(1);
			}
			LOG.info("Delete topics completed");
		}
	}

	public void test() {
		try (AdminClient adminClient2 = KafkaAdminClient.create(kafkaAdmin.getConfigurationProperties())) {
			// ListTopicsResult listTopics = adminClient2.listTopics();
		}
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

	// private static Consumer<?, ?> createConsumer() {
//	ConsumerFactory<?, ?> consumerFactory = SpringUtils.getBean(ConsumerFactory.class);
//	return consumerFactory.createConsumer();
//}

}

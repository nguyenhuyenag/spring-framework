package com.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.DeleteTopicsResult;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.ListTopicsOptions;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.bakup.SpringUtils;

@Component
public class KafkaUtils {

	private static final Logger LOG = LoggerFactory.getLogger(KafkaUtils.class);

	@Autowired
	private ConsumerFactory<String, Object> consumerFactory;

//	@Value("${bootstrap.servers}")
//	private String bootstrapServers;

	@Autowired
	private KafkaAdmin kafkaAdmin;

	@Autowired
	private KafkaProperties kafkaProperties;

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	public void showTopicsInfor() {
		// Or 'new KafkaConsumer<>(kafkaProperties.buildConsumerProperties())'
		try (Consumer<String, Object> consumer = consumerFactory.createConsumer()) {
			// Sort by topicname
			Map<String, List<PartitionInfo>> topics = new TreeMap<>(consumer.listTopics());
			if (topics.isEmpty()) {
				System.out.println("No topics!");
			} else {
				topics.forEach((key, value) -> {
					System.out.println("Topic=" + key + ", number of partions=" + value.size());
				});
			}
		}
	}

	public void partitionsFor(String topicName) {
		List<Integer> partitions = new ArrayList<>();
		List<PartitionInfo> topicInfo = kafkaTemplate.partitionsFor(topicName);
		for (PartitionInfo p : topicInfo) {
			partitions.add(p.partition());
		}
		System.out.printf("Topic=%s, partitions=%s\n", topicName, partitions);
	}

	public boolean isBrokerRunning() {
		final int ADMIN_CLIENT_TIMEOUT_MS = 5 * 1000;
		try (AdminClient adminClient = AdminClient.create(kafkaAdmin.getConfigurationProperties())) {
			ListTopicsOptions options = new ListTopicsOptions();
			options.timeoutMs(ADMIN_CLIENT_TIMEOUT_MS);
			// adminClient.listTopics().listings().get();
			adminClient.listTopics(options).names().get();
			return true;
		} catch (InterruptedException | ExecutionException ex) {
			LOG.error("Kafka is not available, timed out after {} ms", ADMIN_CLIENT_TIMEOUT_MS);
		}
		return false;
	}
	
	public void deleteTopics(String... kafkaTopics) {
		try (AdminClient adminClient = AdminClient.create(kafkaAdmin.getConfigurationProperties())) {
			DeleteTopicsResult delete = adminClient.deleteTopics(Arrays.asList(kafkaTopics));
			while (!delete.all().isDone()) {
				// Wait for future task to complete
			}
			LOG.info("Delete topics completed");
		}
	}

	// TODO: In process
	
	public void test() {
		try(AdminClient adminClient2 = KafkaAdminClient.create(kafkaAdmin.getConfigurationProperties())){
			ListTopicsResult listTopics = adminClient2.listTopics();
		}
	}

//	public void showKafkaConfig() {
//		System.out.println("Testttt: " + kafkaProperties.getTemplate());
//		System.out.println("Bootstrap servers: " + kafkaProperties.getBootstrapServers());
//		System.out.println("Default topic: " + kafkaProperties.getTemplate().getDefaultTopic());
//	}

//	private Map<String, Object> config() {
//		// ConsumerFactory<?, ?> consumerFactory = SpringUtils.getBean(ConsumerFactory.class);
//		// kafkaAdmin.getConfigurationProperties()
//		return consumerFactory.getConfigurationProperties();
//	}

//	public static boolean isKafkaRunning(String bootstrapServers) {
//        try {
//            Properties props = new Properties();
//            props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//            AdminClient adminClient = KafkaAdminClient.create(props);
//            adminClient.listTopics().names().get(); // This will try to list Kafka topics
//            adminClient.close();
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }

	public void createTopic(String topicName, int numPartitions) throws InterruptedException, ExecutionException {
		if (isTopicExist(topicName)) {
			System.out.println("Topic '" + topicName + "' already exist!");
			return;
		}
		try (AdminClient adminClient = AdminClient.create(kafkaAdmin.getConfigurationProperties())) {
			NewTopic newTopic = new NewTopic(topicName, numPartitions, (short) 1);
			List<NewTopic> newTopics = Arrays.asList(newTopic);
			CreateTopicsResult createTopics = adminClient.createTopics(newTopics);
			// Wait for the creation to complete
			KafkaFuture<Void> allCreationFutures = createTopics.all();
			allCreationFutures.get(); // This will block until the creation is complete
		}
	}

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

	public boolean isTopicExist(String topicName) throws InterruptedException, ExecutionException {
		try (AdminClient adminClient = AdminClient.create(kafkaAdmin.getConfigurationProperties())) {
			ListTopicsOptions options = new ListTopicsOptions();
			options.listInternal(true); // includes internal topics such as __consumer_offsets
			ListTopicsResult topics = adminClient.listTopics(options);
			Set<String> currentTopicList = topics.names().get();
			System.out.println("AllTopics: " + currentTopicList);
			return currentTopicList.contains(topicName);
		}
	}

	// List<PartitionInfo> partitionsFor = kafkaTemplate.partitionsFor(topicName);
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
		try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(kafkaAdmin.getConfigurationProperties())) {
			Set<TopicPartition> partitions = listTopicPartition();
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

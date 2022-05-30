package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.kafka.core.ConsumerFactory;

import com.util.KafkaUtils;

@SpringBootApplication
public class Application extends SpringBootServletInitializer implements CommandLineRunner {

	/* WAR */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	ConsumerFactory<?, ?> consumerFactory;

	// @Autowired
	// private MessageService messageService;

	@Value("${kafka.consumer.topic}")
	String topic;

	//private static long startTimestamp = 1653035185447l;

	@Override
	public void run(String... args) throws Exception {
		// System.out.println("checkkkkkkkkkkkkkkkkkkk" + KafkaUtils.isBrokerRunning());
		KafkaUtils.showTopicsInfor();
		System.out.println(topic);
	}

//	public void showTopic() {
//		Map<String, Object> config = consumerFactory.getConfigurationProperties();
//		try (AdminClient adminClient = AdminClient.create(config);) {
//			ListTopicsOptions listTopicsOptions = new ListTopicsOptions();
//			listTopicsOptions.listInternal(true);
//			System.out.println(adminClient.listTopics(listTopicsOptions).names().get());
//		} catch (InterruptedException | ExecutionException e) {
//			e.printStackTrace();
//		}
//	}

//	public void deleteKafkaTopics(List<String> kafkaTopics) {
//		Map<String, Object> config = consumerFactory.getConfigurationProperties();
//		try (AdminClient adminClient = AdminClient.create(config);) {
//			DeleteTopicsResult deleteTopicsResult = adminClient.deleteTopics(kafkaTopics);
//			while (!deleteTopicsResult.all().isDone()) {
//				// Wait for future task to complete
//			}
//		}
//	}

//	void createTopic() {
//		Map<String, Object> config = consumerFactory.getConfigurationProperties();
//		try (AdminClient adminClient = AdminClient.create(config);) {
//			// new NewTopic(topicName, numPartitions, replicationFactor)
//			NewTopic newTopic = new NewTopic("topicName2022", 12, (short) 1);
//			List<NewTopic> newTopics = Arrays.asList(newTopic);
//			adminClient.createTopics(newTopics);
//		}
//	}

//	public void showTopicInfor() {
//		Map<String, Object> config = consumerFactory.getConfigurationProperties();
//		try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(config);) {
//			Map<String, List<PartitionInfo>> topics = consumer.listTopics();
//			for (Map.Entry<String, List<PartitionInfo>> entry : topics.entrySet()) {
//				// System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
//				System.out.println("Topic name: " + entry.getKey());
//				System.out.println("Partions: " + Integer.toString(entry.getValue().size()) + "\n");
//			}
//		}
//	}

//	void seek() {
//		Consumer<?, ?> consumer = consumerFactory.createConsumer();
//		String TOPIC = "topicName2022";
//		// get info of all partitions of a topic
//		List<PartitionInfo> partitionsInfo = consumer.partitionsFor(TOPIC);
//		// create TopicPartition list
//		Set<TopicPartition> partitions = new HashSet<>();
//		for (PartitionInfo p : partitionsInfo) {
//			partitions.add(new TopicPartition(p.topic(), p.partition()));
//		}
//		// Consumer will read from all partitions
//		consumer.assign(partitions);
//
//		Map<TopicPartition, Long> timestamps = new HashMap<>();
//		for (TopicPartition tp : partitions) {
//			timestamps.put(tp, startTimestamp);
//		}
//		// get the offset for that time in each partition
//		Map<TopicPartition, OffsetAndTimestamp> offsets = consumer.offsetsForTimes(timestamps);
//		for (TopicPartition tp : partitions) {
//			System.out.println(offsets.get(tp).offset());
//			consumer.seek(tp, offsets.get(tp).offset());
//		}
//		int count = 0;
//		while (true) {
//			final ConsumerRecords<?, ?> consumerRecords = consumer.poll(Duration.ofMillis(100));
//			for (ConsumerRecord<?, ?> record : consumerRecords) {
//				count++;
//				// record.key()
//				System.out.println("Partition: " + record.partition() + ", Offset:" + record.offset() + ", Value: "
//						+ record.value() + ", Time: " + record.timestamp());
//				if (count >= 5) {
//					consumer.close();
//					return;
//				}
//			}
//		}
//	}

//	public static class SeekToTimeOnRebalance implements ConsumerRebalanceListener {
//		private Consumer<?, ?> consumer;
//		private final Long startTimestamp;
//
//		public SeekToTimeOnRebalance(Consumer<?, ?> consumer, Long startTimestamp) {
//			this.consumer = consumer;
//			this.startTimestamp = startTimestamp;
//		}
//
//		@Override
//		public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
//			Map<TopicPartition, Long> timestampsToSearch = new HashMap<>();
//			for (TopicPartition partition : partitions) {
//				timestampsToSearch.put(partition, startTimestamp);
//			}
//			// for each assigned partition, find the earliest offset in that partition with
//			// a timestamp
//			// greater than or equal to the input timestamp
//			Map<TopicPartition, OffsetAndTimestamp> outOffsets = consumer.offsetsForTimes(timestampsToSearch);
//			for (TopicPartition partition : partitions) {
//				long seekOffset = outOffsets.get(partition).offset();
//				long currentPosition = consumer.position(partition);
//				// seek to the offset returned by the offsetsForTimes API
//				// if it is beyond the current position
//				if (seekOffset >= currentPosition) {
//					consumer.seek(partition, seekOffset);
//				}
//			}
//		}
//
//		@Override
//		public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
//			
//		}
//
//	}

}

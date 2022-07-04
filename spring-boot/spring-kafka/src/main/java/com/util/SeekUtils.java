package com.util;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndTimestamp;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.ConsumerFactory;

public class SeekUtils {

	// private static final Logger LOG = LoggerFactory.getLogger(SeekUtils.class);

	@Autowired
	private ConsumerFactory<?, ?> consumerFactory;

	@Value("${kafka.consumer.topic}")
	private String topicName;

	public boolean seek() {
		long startTime = 1234, endTime = 5678;
		Consumer<?, ?> consumer = setConsumer(startTime);
		while (true) {
			final ConsumerRecords<?, ?> records = consumer.poll(Duration.ofMillis(100));
			for (ConsumerRecord<?, ?> record : records) {
				System.out.println(record.value().toString());
				if (record.timestamp() > endTime) {
					consumer.close();
					return false;
				}
			}
		}
	}
	
	private Consumer<?, ?> setConsumer(long startTime) {
		Consumer<?, ?> consumer = consumerFactory.createConsumer();
		Set<TopicPartition> partitions = KafkaUtils.listTopicPartition();
		consumer.assign(partitions);
		Map<TopicPartition, Long> timestamps = new HashMap<>();
		for (TopicPartition tp : partitions) {
			timestamps.put(tp, startTime);
		}
		Map<TopicPartition, OffsetAndTimestamp> offsets = consumer.offsetsForTimes(timestamps);
		for (TopicPartition tp : partitions) {
			if (offsets.get(tp) != null) {
				consumer.seek(tp, offsets.get(tp).offset());
			}
		}
		return consumer;
	}
	
//	private void countUnConsumer() {
//		Consumer<?, ?> consumer = KafkaUtils.createConsumer();
//		Set<TopicPartition> partitions = KafkaUtils.listTopicPartition();
//		Map<TopicPartition, Long> offsets = consumer.endOffsets(partitions);
//		for (TopicPartition tp : offsets.keySet()) {
//            OffsetAndMetadata commitOffset = consumer.committed(new TopicPartition(tp.topic(), tp.partition()));
//            long lag = commitOffset == null ? offsets.get(tp) : offsets.get(tp) - commitOffset.offset();
//            System.out.println(lag);
//        }
//	}
	
	public static void main(String[] args) {
		// countUnConsumer();
	}

}

package com.kafka.consumer;

import com.kafka.config.KafkaConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/*
	- Mỗi phương thức được đánh dấu @KafkaListener sẽ chạy trên 1 thread riêng biệt
 	- Nếu cần custom container factory:
		@Bean
		public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(
				ConsumerFactory<String, String> consumerFactory) {
			ConcurrentKafkaListenerContainerFactory<String, String> factory =
					new ConcurrentKafkaListenerContainerFactory<>();
			factory.setConsumerFactory(consumerFactory);
			factory.setConcurrency(12); // 12 thread
			return factory;
		}

		Công thức: concurrency = số partition / cho số instance.

			1 instance (1 JVM), cấu hình concurrency = 12
 */
@Slf4j
@Component
public class ConsumerConcurrency {

    // Tự động rebalance khi scale ngang
    @KafkaListener(
            id = "orderListener",
            topics = "${kafka.consumer.topicName}",
            groupId = KafkaConstant.CONSUMER_GROUP_ID,
            containerFactory = KafkaConstant.KAFKA_LISTENER_CONTAINER_FACTORY,
            concurrency = "12"   // 12 thread = 12 partition
    )
    public void listen(String message) {
        System.out.println("Thread ID: " + Thread.currentThread().getId());
        System.out.println("Received message: " + message);
    }

	/*
		// Cách 2:
		@KafkaListener(
				id = "orderListener",
				topics = "${kafka.topic.consumer}",
				groupId = KafkaConstant.CONSUMER_GROUP_ID,
				containerFactory = KafkaConstant.KAFKA_LISTENER_CONTAINER_FACTORY,
				concurrency = "12"
		)
		public void listen(ConsumerRecord<String, String> record) {
			int partition = record.partition();
			System.out.printf("Partition=%d, Thread ID=%d%n", partition, Thread.currentThread().getId());
			// Có thể route theo partition nếu cần
			switch (partition) {
				case 0 -> handlePartition0(record.value());
				case 1 -> handlePartition1(record.value());
				// ...
				default -> handleDefault(record.value());
			}
		}

		private void handleDefault(String value) {
		}

		private void handlePartition1(String value) {
		}

		private void handlePartition0(String value) {
		}
    */

}

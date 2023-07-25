package com.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.kafka.config.KafkaConstant;

/*-
 * - Mỗi phương thức được đánh dấu @KafkaListener sẽ chạy trên 1 thread riêng biệt
 * 
 * 		+ concurrency = "3": Spring Kafka sẽ tạo ra 3 luồng riêng biệt để chạy phương thức
 * 
 * 		+ containerFactory: The bean name of the KafkaListenerContainerFactory to use to create
 * 							the message listener container responsible to serve this endpoint
 * 
 * 
 */
@Component
public class KafkaConsumer {

	private static final Logger LOG = LoggerFactory.getLogger(KafkaConsumer.class);

	@KafkaListener( //
		// id = "id0", //
		// concurrency = "3",
		topics = "${kafka.consumer.topicName}", //
		autoStartup = "${kafka.consumer.autoStartup}", //
		groupId = KafkaConstant.CONSUMER_GROUP_ID, //
		containerFactory = KafkaConstant.KAFKA_LISTENER_CONTAINER_FACTORY //
	)
	public void consumer( //
			@Payload(required = false) String message, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
			@Header(required = false, name = KafkaHeaders.OFFSET) String offset,
			@Header(required = false, name = KafkaHeaders.RECEIVED_PARTITION_ID) String partition) {
		LOG.info("Consumer 1: ThreadID={}", Thread.currentThread().getId());
		LOG.info("Consumer 1: topic={}, offset={}, partition={}, message=[{}]", topic, offset, partition, message);
	}

}
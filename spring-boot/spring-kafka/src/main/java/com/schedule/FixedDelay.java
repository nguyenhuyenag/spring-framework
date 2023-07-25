package com.schedule;

import java.util.Date;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.util.ConfigReader;

@Component
@ConditionalOnProperty(value = "scheduled.enabled", havingValue = "true", matchIfMissing = true)
public class FixedDelay {

	private static final Logger LOG = LoggerFactory.getLogger(FixedDelay.class);

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	@Scheduled(fixedDelayString = "${scheduled.repeate}")
	public void scheduleFixedDelayTask() {
		LOG.info("Fixed delay task - {}", new Date());
		doSend();
	}

	public void doSend() {
		String message = "Data " + System.currentTimeMillis();
		ListenableFuture<SendResult<String, Object>> future //
				= kafkaTemplate.send(ConfigReader.KAFKA_PRODUCER_TOPIC, message);
		future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
			@Override
			public void onSuccess(SendResult<String, Object> result) {
				RecordMetadata record = result.getRecordMetadata();
				LOG.info("onSuccess: topic={}, offset={}, partition={}", record.topic(), record.offset(),
						record.partition());
			}

			@Override
			public void onFailure(Throwable ex) {
				LOG.warn("onFailure: {}", message, ex.getMessage());
			}
		});
	}

}

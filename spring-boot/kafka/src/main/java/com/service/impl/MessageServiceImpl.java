package com.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.model.Message;
import com.repository.MessageRepository;
import com.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

	private static final Logger LOG = LoggerFactory.getLogger(MessageServiceImpl.class);

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	@Autowired
	private MessageRepository repository;

	@Value("${kafka.topic.producer}")
	private String KAFKA_TOPIC_PRODUCER;

	public static int randomIntFrom(int min, int max) {
		if (max <= min) {
			throw new IllegalArgumentException("Max must be greater than min");
		}
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}

	public static String longText() {
		int n = randomIntFrom(1, 100000);
		return RandomStringUtils.random(n);
	}

	@Override
	public void send() {
		DateFormat df = new SimpleDateFormat("dd-MM-yyy HH:mm:ss");
		while (true) {
			Date date = new Date();
			String message = date.getTime() + " " + df.format(date);
			ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(KAFKA_TOPIC_PRODUCER, message);
			future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
				@Override
				public void onSuccess(SendResult<String, Object> result) {
					LOG.info("Send successfull, partition = {}, offset = {}", result.getRecordMetadata().partition(),
							result.getRecordMetadata().offset());
				}

				@Override
				public void onFailure(Throwable e) {
					LOG.info("Send fail: {}", e.getMessage());
				}
			});
			try {
				Thread.sleep(1 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void received(String message) {
		Message entity = new Message(message);
		repository.save(entity);
	}

}

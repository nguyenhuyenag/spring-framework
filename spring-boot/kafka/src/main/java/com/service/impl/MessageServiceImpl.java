package com.service.impl;

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
import com.util.Base64Utils;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	@Autowired
	private MessageRepository repository;

	@Value("${kafka.topic.producer}")
	private String KAFKA_TOPIC_PRODUCER;

	@Override
	public void send(int amount) {
		String message = Base64Utils.longText();
		for (int i = 0; i < amount; i++) {
			ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(KAFKA_TOPIC_PRODUCER, message);
			future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
				@Override
				public void onSuccess(SendResult<String, Object> result) {
					// System.out.println("Send successfull");
				}
				@Override
				public void onFailure(Throwable e) {
					System.out.println("Send fail");
				}
			});
		}
	}

	@Override
	public void received(String message) {
		Message entity = new Message(message);
		repository.save(entity);
	}

}

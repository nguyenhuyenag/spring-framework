//package com.service.impl;
//
//import java.util.Date;
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.Future;
//import java.util.concurrent.ThreadLocalRandom;
//
//import org.apache.commons.lang3.RandomStringUtils;
//import org.apache.kafka.clients.producer.RecordMetadata;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.support.SendResult;
//import org.springframework.stereotype.Service;
//import org.springframework.util.concurrent.ListenableFuture;
//import org.springframework.util.concurrent.ListenableFutureCallback;
//
//import com.model.Message;
//import com.repository.MessageRepository;
//import com.service.MessageService;
//import com.util.DatetimeUtils;
//
//@Service
//public class MessageServiceImpl implements MessageService {
//
//	private static final Logger LOG = LoggerFactory.getLogger(MessageServiceImpl.class);
//
//	@Autowired
//	private KafkaTemplate<String, Object> kafkaTemplate;
//
//	@Autowired
//	private MessageRepository repository;
//
//	@Value("${kafka.topic.producer}")
//	private String KAFKA_TOPIC_PRODUCER;
//
//	public static int randomIntFrom(int min, int max) {
//		if (max <= min) {
//			throw new IllegalArgumentException("Max must be greater than min");
//		}
//		return ThreadLocalRandom.current().nextInt(min, max + 1);
//	}
//
//	public static String longText() {
//		int n = randomIntFrom(1, 100000);
//		return RandomStringUtils.randomAlphabetic(n);
//	}
//
//	@Override
//	public void send() {
//		while (true) {
//			Date date = new Date(); 
//			String message = date.getTime() + "______" + DatetimeUtils.format(date);
//			ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(KAFKA_TOPIC_PRODUCER, message);
//			future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
//				@Override
//				public void onSuccess(SendResult<String, Object> result) {
//					RecordMetadata record = result.getRecordMetadata();
//					LOG.info("Send successfull, partition = {}, offset = {}", record.partition(), record.offset());
//				}
//
//				@Override
//				public void onFailure(Throwable e) {
//					LOG.info("Send fail: {}", e.getMessage());
//				}
//			});
//			try {
//				Thread.sleep(5 * 1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//	@Override
//	public void received(String message) {
//		repository.save(new Message(message));
//	}
//
//	@Override
//	public void receivedByThread(String message) {
//		ExecutorService executor = Executors.newSingleThreadExecutor();
//		ThreadReceive threadReceive = new ThreadReceive(message);
//		Future<?> f = executor.submit(threadReceive);
//		try {
//			LOG.info("receivedByThread: {}", f.get());
//		} catch (InterruptedException | ExecutionException e) {
//			e.printStackTrace();
//		}
//		executor.shutdown();
//	}
//
//}

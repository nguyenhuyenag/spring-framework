package com.bakup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.util.ConfigReader;

import lombok.NoArgsConstructor;

// @Component
@NoArgsConstructor
public class DataRunable implements Runnable {

	private static final Logger LOG = LoggerFactory.getLogger(DataRunable.class);

	@Autowired
	private DataService dataService;

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	private int threadname;
	private List<Data> listData;

	private static final Set<String> poolIds = Collections.synchronizedSet(new HashSet<>());

	@SuppressWarnings("unchecked")
	public void init() {
		this.kafkaTemplate = SpringUtils.getBean(KafkaTemplate.class);
		this.dataService = SpringUtils.getBean(DataService.class);
	}

	public DataRunable(int threadname, List<Data> data) {
		this.threadname = threadname;
		this.listData = new ArrayList<>(data);
	}

	@Override
	public void run() {
		doSend();
	}

	private void doSend() {
		init();
		LOG.info("Job {}, thread {} start, data  = {}", JobPutData.nJob, threadname, listData.size());
		Set<String> setIds = new HashSet<>();
		for (Data data : listData) {
			String code = data.getCode();
			try {
				if (poolIds.add(code)) {
					setIds.add(code);
//					CompletableFuture<ListenableFuture<SendResult<String, Object>>> f = CompletableFuture.supplyAsync(
//							() -> kafkaTemplate.send(ConfigReader.KAFKA_PRODUCER_TOPIC, data.getContent()));
//					f.thenAccept(t -> {
//						t.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
//							@Override
//							public void onSuccess(SendResult<String, Object> result) {
//								LOG.info("Job {}, thread {}, send success: {}", JobPutData.nJob, threadname, data.getCode());
//								dataService.onSuccess(data);
//							}
//
//							@Override
//							public void onFailure(Throwable e) {
//								LOG.info("Send fail: {}", data.getCode());
//								LOG.error(e.getMessage());
//							}
//						});
//					});
					
					ListenableFuture<SendResult<String, Object>> future = //
							kafkaTemplate.send(ConfigReader.KAFKA_PRODUCER_TOPIC, data.getContent());

//					while (!future.isDone()) {
//						// LOG.info("Wait future is done");
//					}
					
					// Async.await(future);
					
//					future.addCallback(success -> {
//				    }, failure -> {
//				    });
					
					future.get(30, TimeUnit.SECONDS);
					future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
						@Override
						public void onSuccess(SendResult<String, Object> result) {
							dataService.onSuccess(data);
						}

						@Override
						public void onFailure(Throwable e) {
							LOG.info("Send fail: {}", data.getCode());
							LOG.error(e.getMessage());
						}
					});
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		poolIds.removeAll(setIds);
	}

//	@Async
//    public void send(String topic, String message) {
//        ListenableFuture<SendResult<String, GenericMessage>> future = kafkaTemplate.send(topic, message);
//        future.addCallback(new ListenableFutureCallback<SendResult<String, GenericMessage>>() {
//            @Override
//            public void onSuccess(final SendResult<String, GenericMessage> message) {
//                LOG.info("sent message= " + message + " with offset= " + message.getRecordMetadata().offset());
//            }
//
//            @Override
//            public void onFailure(final Throwable throwable) {
//                LOG.error("unable to send message= " + message, throwable);
//            }
//        });
//    }

}

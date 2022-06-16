package com.schedule.runnable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.model.LIpsum;
import com.schedule.jobs.JobPut;
import com.service.DataService;
import com.util.Base64Utils;
import com.util.ConfigReader;
import com.util.JsonUtils;
import com.util.SpringUtils;

import lombok.NoArgsConstructor;

// @Component
@NoArgsConstructor
public class LoremRunable implements Runnable {

	private static final Logger LOG = LoggerFactory.getLogger(LoremRunable.class);

	@Autowired
	private DataService dataService;

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	private int threadname;
	private List<LIpsum> data;

	private static final Set<String> poolIds = Collections.synchronizedSet(new HashSet<>());

	@SuppressWarnings("unchecked")
	public void init() {
		this.kafkaTemplate = SpringUtils.getBean(KafkaTemplate.class);
		this.dataService = SpringUtils.getBean(DataService.class);
	}

	public LoremRunable(int threadname, List<LIpsum> data) {
		this.threadname = threadname;
		this.data = new ArrayList<>(data);
	}

	@Override
	public void run() {
		doSend();
	}

	private void doSend() {
		init();
		LOG.info("Job {}, thread {} start, data  = {}", JobPut.nJob, threadname, data.size());
		for (LIpsum ipsum : data) {
			try {
				String code = ipsum.getCode();
				if (poolIds.add(code)) {
					String message = Base64Utils.encodeToString(JsonUtils.toJSON(ipsum));
					ListenableFuture<SendResult<String, Object>> future = //
							kafkaTemplate.send(ConfigReader.KAFKA_PRODUCER_TOPIC, message);

					while (!future.isDone()) {
						// LOG.info("Wait future is done");
					}

					future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
						@Override
						public void onSuccess(SendResult<String, Object> result) {
							LOG.info("Job {}, thread {}, send success: {}", JobPut.nJob, threadname, ipsum.getCode());
							dataService.onSuccess(ipsum);
						}

						@Override
						public void onFailure(Throwable e) {
							LOG.info("Send fail: {}", ipsum.getCode());
							LOG.error(e.getMessage());
						}
					});
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
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

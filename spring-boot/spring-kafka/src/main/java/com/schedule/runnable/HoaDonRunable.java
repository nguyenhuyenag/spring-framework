package com.schedule.runnable;

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

import com.model.LIpsum;
import com.schedule.jobs.JobPut;
import com.service.DataService;
import com.util.ConfigReader;
import com.util.SpringUtils;

import lombok.NoArgsConstructor;

// @Component
@NoArgsConstructor
public class HoaDonRunable implements Runnable {

	private static final Logger LOG = LoggerFactory.getLogger(HoaDonRunable.class);

	@Autowired
	private DataService hoadonService;

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	private int threadname;
	private List<LIpsum> data;

	private static final Set<String> poolIds = Collections.synchronizedSet(new HashSet<>());

	@SuppressWarnings("unchecked")
	public void init() {
		this.kafkaTemplate = SpringUtils.getBean(KafkaTemplate.class);
		this.hoadonService = SpringUtils.getBean(DataService.class);
	}

	public HoaDonRunable(int threadname, List<LIpsum> data) {
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
		for (LIpsum hoadon : data) {
			try {
				String guid = "xxxxxxxxxxxxxxxx";
				if (poolIds.add(guid)) {
					// String message = hoadon.getNoidungGui();
					ListenableFuture<SendResult<String, Object>> future = //
							kafkaTemplate.send(ConfigReader.KAFKA_PRODUCER_TOPIC, "XXXXXXXX");

					while (!future.isDone()) {
						// LOG.info("Wait future is done");
					}

					future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
						// String mtdiep = hoadon.getMatdiep();
						@Override
						public void onSuccess(SendResult<String, Object> result) {
							// LOG.info("Job {}, thread {}, success: {}", PutJobs.nJob, threadname, mtdiep);
							// wait 1s update database
							try {
								TimeUnit.SECONDS.sleep(1);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							// hoadonService.onSuccess(hoadon);
						}

						@Override
						public void onFailure(Throwable e) {
							// LOG.info("Send fail: {}", mtdiep);
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

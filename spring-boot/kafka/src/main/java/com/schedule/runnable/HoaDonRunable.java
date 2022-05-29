package com.schedule.runnable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.model.HoaDon;
import com.schedule.jobs.PutHoaDon;
import com.service.HoaDonService;
import com.util.ConfigReader;
import com.util.SpringUtils;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class HoaDonRunable implements Runnable {

	private static final Logger LOG = LoggerFactory.getLogger(HoaDonRunable.class);

	@Autowired
	private HoaDonService hoadonService;

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	private int threadname;
	private List<HoaDon> data;

	private static final Set<String> poolIds = Collections.synchronizedSet(new HashSet<>());

	@SuppressWarnings("unchecked")
	public void init() {
		this.kafkaTemplate = SpringUtils.getBean(KafkaTemplate.class);
		this.hoadonService = SpringUtils.getBean(HoaDonService.class);
	}

	public HoaDonRunable(int threadname, List<HoaDon> data) {
		this.threadname = threadname;
		this.data = new ArrayList<>(data);
	}

	@Override
	public void run() {
		doSend();
	}
	
	private static int countSend = 0;
	
	public static int getCountSend() {
		return countSend;
	}
	
	public static int randomIntFrom(int min, int max) {
		if (max <= min) {
			throw new IllegalArgumentException("Max must be greater than min");
		}
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}

	private void doSend() {
		try {
			int time = randomIntFrom(2, 5);
			LOG.info("Sleep {}s before thread {} start", time, threadname);
			TimeUnit.SECONDS.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		init();
		LOG.info("Job {}, thread {} start, data  = {}", PutHoaDon.jobCount, threadname, data.size());
		try {
			for (HoaDon hoadon : data) {
				String guid = hoadon.getGuid().trim();
				if (poolIds.add(guid)) {
					String message = hoadon.getNoidungGui();
					ListenableFuture<SendResult<String, Object>> future = //
							kafkaTemplate.send(ConfigReader.KAFKA_PRODUCER_TOPIC, message);
					future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
						@Override
						public void onSuccess(SendResult<String, Object> result) {
							LOG.info("Job {}, thread {}, success: {}", PutHoaDon.jobCount, threadname, hoadon.getMatdiep());
							hoadonService.updateTinhTrangGui(guid);
							countSend++;
						}

						@Override
						public void onFailure(Throwable e) {
							LOG.info("Send fail: {}", hoadon.getMatdiep());
							LOG.error(e.getMessage());
						}
					});
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

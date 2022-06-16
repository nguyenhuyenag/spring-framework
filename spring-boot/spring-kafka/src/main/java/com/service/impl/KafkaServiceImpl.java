package com.service.impl;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.stereotype.Service;

import com.service.KafkaService;

@Service
public class KafkaServiceImpl implements KafkaService {

	private static final Logger LOG = LoggerFactory.getLogger(KafkaServiceImpl.class);

	@Autowired
	private KafkaListenerEndpointRegistry registry;

	private final int PARTITIONS = 12;

	@Override
	public void triggerConsumer(boolean trigger) {
		if (trigger) {
			startAll();
		} else {
			stopAll();
		}
	}

	private void startAll() {
		for (int i = 0; i < PARTITIONS; i++) {
			String id = "id" + i;
			MessageListenerContainer listener = registry.getListenerContainer(id);
			if (Objects.isNull(listener)) {
				LOG.info("Consumer with id={} is not found", id);
			} else {
				if (listener.isRunning()) {
					LOG.info("Consumer with id={} is already start", id);
				} else {
					listener.start();
					LOG.info("Start consumer with id={}", id);
				}
			}
		}
	}

	private void stopAll() {
		for (int i = 0; i < PARTITIONS; i++) {
			String id = "id" + i;
			MessageListenerContainer listener = registry.getListenerContainer(id);
			if (Objects.isNull(listener)) {
				LOG.info("Consumer with id={} is not found", id);
			} else {
				if (!listener.isRunning()) {
					LOG.info("Consumer with id={} is already stop", id);
				} else {
					listener.stop();
					LOG.info("Stop consumer with id={}", id);
				}
			}
		}
	}

//	private void trigger(boolean trigger) {
//		String action = trigger ? "Start" : "Stop";
//		for (int i = 0; i < 12; i++) {
//			String id = "id" + i;
//			MessageListenerContainer listener = registry.getListenerContainer(id);
//			if (Objects.isNull(listener)) {
//				LOG.info("Consumer with id {} is not found", id);
//			} else {
//				if (trigger) {
//					if (listener.isRunning()) {
//						LOG.info("Consumer with id {} is already start", id);
//					} else {
//						listener.start();
//						LOG.info("{} consumer with id={}", action, id);
//					}
//				} else {
//					if (!listener.isRunning()) {
//						LOG.info("Consumer with id {} is already stop", id);
//					} else {
//						listener.stop();
//						LOG.info("{} consumer with id={}", action, id);
//					}
//				}
//			}
//		}
//	}

	// public void startListener(String groupId) {
	// System.out.println("Start " + groupId);
	// kafkaListenerEndpointRegistry.getListenerContainer(groupId).start();
	// }
	//
	// public void stopListener(String groupId) {
	// kafkaListenerEndpointRegistry.getListenerContainer(groupId).stop(() -> {
	// System.out.println("Listener Stopped.");
	// });
	// }

}

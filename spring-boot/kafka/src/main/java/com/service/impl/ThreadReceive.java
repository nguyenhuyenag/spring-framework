package com.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.model.Message;
import com.repository.MessageRepository;
import com.util.SpringUtils;

@Service
@Configurable
// @NoArgsConstructor
public class ThreadReceive implements Runnable {
	
	private static final Logger LOG = LoggerFactory.getLogger(ThreadReceive.class);

	// @Autowired
	private MessageRepository repository;

	private String content;
	
	public ThreadReceive() {}

////	@Autowired
//	public ThreadReceive(MessageRepository repository) {
//		this.repository = repository;
//	}

	public ThreadReceive(String content) {
		this.content = content;
	}

	@Override
	public void run() {
		repository = SpringUtils.CTX.getBean(MessageRepository.class);
		if (repository != null) {
			repository.save(new Message(content));
		} else {
			LOG.info("Repository is NULL!");
			System.exit(-1);
		}
	}

}

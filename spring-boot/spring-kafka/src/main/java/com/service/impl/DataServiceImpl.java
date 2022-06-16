package com.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.LIpsum;
import com.model.ReceiveMessage;
import com.repository.LIpsumRepository;
import com.repository.ReceiveMessageRepository;
import com.service.DataService;
import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import com.util.Base64Utils;
import com.util.JsonUtils;
import com.util.RandomUtils;

@Service
public class DataServiceImpl implements DataService {

	private static final Logger LOG = LoggerFactory.getLogger(DataServiceImpl.class);

	@Autowired
	private LIpsumRepository lipsumRepository;

	@Autowired
	private ReceiveMessageRepository receiveMessageRepository;

	@Override
	public void autoInsert() {
		Lorem lorem = LoremIpsum.getInstance();
		int n = RandomUtils.randomInteger(299, 501);
		for (int i = 0; i < n; i++) {
			LIpsum entity = new LIpsum();
			entity.setCode(RandomUtils.initCode());
			entity.setContent(lorem.getParagraphs(50, 200));
			if (lipsumRepository.save(entity) != null) {
				LOG.info("Save {} to lorem_ipsum", entity.getCode());
			}
		}
	}

	@Override
	public void onSuccess(LIpsum ipsum) {
		lipsumRepository.updateStatus(ipsum.getCode(), 1);
	}

	@Override
	public void receiveMessage(String message) {
		LIpsum ipsum = JsonUtils.toObject(Base64Utils.decodeToString(message), LIpsum.class);
		if (ipsum != null) {
			ReceiveMessage entity = new ReceiveMessage();
			entity.setCode(ipsum.getCode());
			entity.setContent(message);
			if (receiveMessageRepository.save(entity) != null) {
				LOG.info("Save {} to receive_message", entity.getCode());
			}
		}
	}

}

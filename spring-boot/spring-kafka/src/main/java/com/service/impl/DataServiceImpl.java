package com.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Data;
import com.entity.DataReceived;
import com.repository.DataRepository;
import com.repository.DataReceivedRepository;
import com.service.DataService;
import com.util.Base64Utils;
import com.util.RandomUtils;

@Service
public class DataServiceImpl implements DataService {

	private static final Logger LOG = LoggerFactory.getLogger(DataServiceImpl.class);

	@Autowired
	private DataRepository dataRepository;

	@Autowired
	private DataReceivedRepository dataReceivedRepository;

	@Override
	public void autoInsert() {
		List<Data> list = new ArrayList<>();
		int n = RandomUtils.randomInteger(50, 100);
		for (int i = 0; i < n; i++) {
			String code = RandomUtils.initCode();
			Data entity = new Data();
			entity.setCode(code);
			entity.setContent(Base64Utils.encodeToString(code));
			list.add(entity);
		}
		if (dataRepository.saveAll(list) != null) {
			LOG.info("Save {} record to lorem_ipsum", list.size());
		}
	}

	@Override
	public void onSuccess(Data ipsum) {
		dataRepository.updateStatus(ipsum.getCode(), 1);
	}

	@Override
	public void receiveMessage(String listener, String message) {
		try {
			String code = Base64Utils.decodeToString(message);
			if (StringUtils.isNotEmpty(code)) {
				DataReceived entity = new DataReceived();
				entity.setDataCode(code);
				entity.setContent(message);
				entity.setListener(listener);
				if (dataReceivedRepository.existsByDataCode(code)) {
					LOG.info("Duplicate save data code={} to data_received", code);
					entity.setNote("Duplicate");
				}
				if (dataReceivedRepository.save(entity) != null) {
					LOG.info("Save {} to data_received", code);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.TransactionTemplate;

import com.second.repository.OfficeRepository;
import com.util.DataUtils;

@Service
public class MyTransactionService2 {

	@Autowired
	OfficeRepository officeRepository;

	@Autowired
	@Qualifier("tt2")
	TransactionTemplate transactionTemplate;

	public void testTransaction() {
		withAspect();
		// withTemplate();
	}

	@Transactional("tm2")
	public void withAspect() {
		try {
			officeRepository.save(DataUtils.getOff1());
			officeRepository.save(DataUtils.getOff2());
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}

	public void withTemplate() {
		transactionTemplate.executeWithoutResult(status -> {
			try {
				officeRepository.save(DataUtils.getOff1());
				officeRepository.save(DataUtils.getOff2());
			} catch (Exception e) {
				e.printStackTrace();
				status.setRollbackOnly();
			}
		});
	}

}

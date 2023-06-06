package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.TransactionTemplate;

import com.second.repository.OfficeRepository;
import com.util.BeanName;
import com.util.DataUtils;

@Service
public class MyTransactionService2 {

	@Autowired
	OfficeRepository officeRepository;

	@Autowired
	@Qualifier(BeanName.DB2_TRANSACTION_TEMPLATE)
	TransactionTemplate transactionTemplate;

	public void testTransaction() {
		// withTemplate();
		withAspectSupport();
	}

	// Not working
	@Transactional(value = BeanName.DB2_TRANSACTION_MANAGER)
	public void withAspectSupport() {
		TransactionStatus cts = TransactionAspectSupport.currentTransactionStatus();
		if (cts == null) {
			System.out.println("No TransactionStatus");
		}
		try {
			officeRepository.save(DataUtils.getOff1());
			officeRepository.save(DataUtils.getOff2());
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}

	// OK
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

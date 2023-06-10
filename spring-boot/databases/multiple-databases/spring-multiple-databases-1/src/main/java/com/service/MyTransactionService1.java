package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.TransactionTemplate;

import com.primary.repository.AddressRepository;
import com.util.DataUtils;

@Service
public class MyTransactionService1 {

	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	TransactionTemplate transactionTemplate;

	public void testTransaction() {
		// withTemplate();
		withAspectSupport();
	}

	// Not working
	@Transactional
	public void withAspectSupport() {
		TransactionStatus cts = TransactionAspectSupport.currentTransactionStatus();
		if (cts == null) {
			System.out.println("No TransactionStatus");
		}
		try {
			addressRepository.save(DataUtils.passAddress());
			addressRepository.save(DataUtils.failAddress());
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}
	
	// OK
	public void withTemplate() {
		transactionTemplate.executeWithoutResult(status -> {
			try {
				addressRepository.save(DataUtils.passAddress());
				addressRepository.save(DataUtils.failAddress());
			} catch (Exception e) {
				e.printStackTrace();
				status.setRollbackOnly();
			}
		});
	}

}

package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.primary.repository.CustomerRepository;
import com.util.DataUtils;

@Service
@Transactional
public class MyTransactionService1 {

	@Autowired
	CustomerRepository customerRepository;

	public void testTransaction() {
		setRollbackOnly();
	}

	public void setRollbackOnly() {
		try {
			customerRepository.save(DataUtils.getC1());
			customerRepository.save(DataUtils.getC2());
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}
	
//	public void withTemplate() {
//		try {
//			customerRepository.save(DataUtils.getC1());
//			customerRepository.save(DataUtils.getC2());
//		} catch (Exception e) {
//			e.printStackTrace();
//			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//		}
//	}

}

package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.primary.repository.CustomerRepository;
import com.second.repository.OfficeRepository;
import com.util.DataUtils;

@Service
@Transactional
public class MyTransactionService {

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	OfficeRepository officeRepository;

	public void testTransaction() {
		test1Repository();
		// test2Repository();
	}

	public void test1Repository() {
		try {
			customerRepository.save(DataUtils.getC1());
			customerRepository.save(DataUtils.getC2());
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}

	public void test2Repository() {
		try {
			officeRepository.save(DataUtils.getOff1());
			officeRepository.save(DataUtils.getOff2());
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}

}

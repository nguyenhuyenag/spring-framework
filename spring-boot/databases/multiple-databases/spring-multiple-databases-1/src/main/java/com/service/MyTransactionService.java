package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.primary.repository.CustomerRepository;
import com.util.DataUtils;

@Service
@Transactional
public class MyTransactionService {

	// @PersistenceContext
	// EntityManager entity1Manager;

	// @Autowired
	// OfficeRepository repository2;

	@Autowired
	CustomerRepository customerRepository;

	public void testTransaction() {
		test1Repository();
		// test2Repository();
	}

	// @Transactional
	public void test1Repository() {
		try {
			customerRepository.save(DataUtils.getC1());
			customerRepository.save(DataUtils.getC2());
		} catch (Exception e) {
			e.printStackTrace();
			// TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}

//	@Transactional(value = "transaction2Manager")
//	public void test2Repository() {
//		repository2.save(getOff1());
//		repository2.save(getOff2());
//	}

}

package com.service.impl;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.entity.User;
import com.repository.UserRepository;
import com.service.SpringTransaction;

@Service
public class SpringTransactionImpl implements SpringTransaction {

	@Autowired
	UserRepository repository;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public void rollBackWithStatus() {
		System.out.println(TransactionAspectSupport.currentTransactionStatus());
		System.out.println(TransactionAspectSupport.currentTransactionStatus().isNewTransaction());
		try {
			for (int i = 1; i <= 3; i++) {
				User entity = new User();
				String name = RandomStringUtils.randomAlphabetic(5).toLowerCase();
				entity.setEmail(name + "@mail.com");
				repository.save(entity);
				System.out.println("OK! ...............");
			}
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			System.out.println("Rollback! .............");
		}
	}

	@Override
	@Transactional(readOnly = false)
	public void testRollBack() {
		try {
			for (int i = 1; i <= 3; i++) {
				User entity = new User();
				String name = RandomStringUtils.randomAlphabetic(5).toLowerCase();
				entity.setName(name);
				entity.setEmail(name + "@mail.com");
				repository.save(entity);
				System.out.println("OK! ...............");
			}
		} catch (Exception e) {
			System.out.println("Exception ....................");
			e.printStackTrace();
		}
	}

	@Override
	public void hibernateTransaction() {

	}

}

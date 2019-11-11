package com.service.impl;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
	@Transactional
	public void rollBackWithStatus() {
		try {
			for (int i = 1; i <= 3; i++) {
				User entity = new User();
				String first = RandomStringUtils.randomAlphabetic(5).toLowerCase();
				String last = RandomStringUtils.randomAlphabetic(5).toLowerCase();
				entity.setFirstname(first);
				if (i == 2) {
					entity.setFirstname(first + first);
				}
				entity.setLastname(last);
				entity.setEmailAddress(first + "@" + last + ".com");
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
				String first = RandomStringUtils.randomAlphabetic(5).toLowerCase();
				String last = RandomStringUtils.randomAlphabetic(5).toLowerCase();
				entity.setFirstname(first);
				if (i == 2) {
					entity.setFirstname(first + first);
				}
				entity.setLastname(last);
				entity.setEmailAddress(first + "@" + last + ".com");
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

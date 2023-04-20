package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.User;
import com.repository.TUserRepository;
import com.service.TransactionSevice;

@Service
public class TransactionSeviceImpl implements TransactionSevice {

	@Autowired
	TUserRepository repository;

	// @Transactional
	@Override
	public void testTransaction() {
		repository.save(new User("Huyen")); // OK
		repository.save(new User("Thien")); // OK
		repository.save(new User("Hoang Thien")); // ERROR
	}

}

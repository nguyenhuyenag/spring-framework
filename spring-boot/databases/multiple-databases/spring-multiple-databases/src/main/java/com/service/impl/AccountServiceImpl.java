package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dto.AccountDto;
import com.entity.primary.User;
import com.repository.primary.UserRepository;
import com.service.AccountService;

@Service
@Transactional("transactionManager")
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;

	@Autowired
	private UserRepository userRepository;

	@Override
	public void register(AccountDto account) {
		User entity = new User();
		entity.setUsername(account.getUsername());
		entity.setFullName(account.getFullName());
		entity.setEmail(account.getEmail());
		entity.setPassword(bcrypt.encode(account.getPassword()));
		userRepository.save(entity);
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

}

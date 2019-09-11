package com.spring.service;

import com.spring.dto.AccountDto;
import com.spring.entity.primary.User;

public interface AccountService {

	void register(AccountDto accountDto);

	User findByEmail(String email);
}

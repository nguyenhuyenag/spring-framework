package com.service;

import com.dto.AccountDto;
import com.entity.primary.User;

public interface AccountService {

	void register(AccountDto accountDto);

	User findByEmail(String email);
}

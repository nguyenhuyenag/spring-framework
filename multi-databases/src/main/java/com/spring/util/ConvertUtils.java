/*package com.spring.util;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.spring.dto.AccountDto;
import com.spring.entity.User;

@Component
public class ConvertDto {
	
	// Can't @Autowired a static fiel, but can use @PostConstruct
	
	private static BCryptPasswordEncoder bcrypt;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@PostConstruct
	private void init() {
		bcrypt = this.bCryptPasswordEncoder;
	}

	public static User toDto(AccountDto dto) {
		User user = new User();
		user.setEmail(dto.getEmail());
		user.setFullName(dto.getFullName());
		user.setPassword(bcrypt.encode(dto.getPassword()));
		return user;
	}

}
*/
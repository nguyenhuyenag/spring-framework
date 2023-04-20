package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dto.AccountDto;
import com.service.AccountService;

@Controller
@RequestMapping("api/account")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@PostMapping("register")
	public ResponseEntity<Void> register(@RequestBody AccountDto account) {
		accountService.register(account);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}

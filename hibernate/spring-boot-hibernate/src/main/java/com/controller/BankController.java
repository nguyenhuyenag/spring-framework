package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.BankService;

@RestController
@RequestMapping("amt")
public class BankController {

	@Autowired
	private BankService service;

	@GetMapping("withdraw")
	private ResponseEntity<Result> withdraw() {
		Result r = service.withdraw();
		return new ResponseEntity<>(r, HttpStatus.OK);
	}

	@GetMapping("deposit")
	private ResponseEntity<Result> deposit() {
		Result r = service.deposit();
		return new ResponseEntity<>(r, HttpStatus.OK);
	}

}

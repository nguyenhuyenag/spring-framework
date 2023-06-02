package com.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.service.JdbcTemplateService;
import com.service.TransactionService;

@Component
public class AppRunner implements CommandLineRunner {

	@Autowired
	JdbcTemplateService service;
	// EntityManagerService service;
	
	@Autowired
	TransactionService transactionService;

	@Override
	public void run(String... args) throws Exception {
		// service.findAll();
		// service.showDataSourceURL();
		transactionService.testTransaction();
	}

}

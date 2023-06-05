package com.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.service.SpringTransaction;

@Component
public class AppRunner implements CommandLineRunner {
	
	@Autowired
	SpringTransaction springTransaction;

	@Override
	public void run(String... args) throws Exception {
		// springTransaction.withStatus();
		// springTransaction.withTransactionTemplate_1();
		springTransaction.withTransactionTemplate_2();
	}

}

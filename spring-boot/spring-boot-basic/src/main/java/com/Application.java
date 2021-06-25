package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.service.SpringTransaction;

@SpringBootApplication
public class Application implements CommandLineRunner {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	SpringTransaction transaction;
		
	@Override
	public void run(String... args) throws Exception {
		// transaction.testRollBack();
		// System.out.println(TransactionAspectSupport.currentTransactionStatus().isNewTransaction());
		transaction.rollBackWithStatus();
	}

}

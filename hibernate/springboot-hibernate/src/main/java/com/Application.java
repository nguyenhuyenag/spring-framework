package com;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.service.impl.BankServiceImpl;

@SpringBootApplication
public class Application implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	// @Autowired
	// BankService atm;

	@Override
	public void run(String... args) throws Exception {
		LOG.info("OK!");
		BankServiceImpl R1 = new BankServiceImpl("ATM 1");
        R1.start();
        BankServiceImpl R2 = new BankServiceImpl("ATM 2");
        R2.start();
	}

}

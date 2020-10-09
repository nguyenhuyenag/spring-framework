package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.service.ManyToManyService;

@SpringBootApplication
public class Application implements CommandLineRunner {

	// private static final Logger LOG = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Autowired
	private ManyToManyService repository;

	@Override
	public void run(String... args) throws Exception {
		// Insert 1 category với nhiều product
		// repository.demoInsert1();
	}

}

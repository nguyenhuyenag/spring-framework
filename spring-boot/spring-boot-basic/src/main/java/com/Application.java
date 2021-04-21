package com;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	// PageableJPA.init();
	// PageableJPA.info();
	// PageableJPA.sortPage();
	// PageableJPA.showAllPage();
	// userRepository.getAllEmails().forEach(t->System.out.println(t));
	// System.out.println(userRepository.getAllEmails());
		
	@Override
	public void run(String... args) throws Exception {
		
	}

}

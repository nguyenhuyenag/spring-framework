package com;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class Application implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		// PageableJPA.init();
		// PageableJPA.info();
		// PageableJPA.sortPage();
		// PageableJPA.showAllPage();
		// userRepository.getAllEmails().forEach(t->System.out.println(t));
		// System.out.println(userRepository.getAllEmails());
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}

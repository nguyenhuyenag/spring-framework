package com;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.model.User;
import com.repository.UserRepository;
import com.test.PageableJPA;

@SpringBootApplication
public class BootApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class, args);
	}
	
	@Autowired
	UserRepository repository;
	
	@Override
	public void run(String... args) throws Exception {
		for (int i = 0; i < 7; i++) {
			User u = new User();
			u.setName(RandomStringUtils.randomAlphanumeric(5).toUpperCase());
			repository.save(u);
		}
		PageableJPA.showAllPage(7);
		// System.out.println("OK");
	}

}

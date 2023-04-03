package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.repository.UserRepository;

@SpringBootApplication
public class BootApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class, args);
	}
	
	@Autowired
	private UserRepository repository;

	// @Transactional
	@Override
	public void run(String... args) throws Exception {
		repository.findById(1).get().getUserRoles().forEach(
			t -> System.out.println(t.getRole())
		);
	}

}

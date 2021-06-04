package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.repository.VocabRepository;
import com.service.FTPService;

@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	VocabRepository repository;
	
	@Autowired
	FTPService service;

	@Override
	public void run(String... args) throws Exception {
		service.exportJSON();
	}

}

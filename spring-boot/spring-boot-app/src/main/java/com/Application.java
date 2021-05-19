package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.service.Service;

@SpringBootApplication
public class Application implements CommandLineRunner {

	// JAR
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Autowired
	Service service;

	@Override
	public void run(String... args) throws Exception {
		System.out.println(service.getJSON());
	}

}

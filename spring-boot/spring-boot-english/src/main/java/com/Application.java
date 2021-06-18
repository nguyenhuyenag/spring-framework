package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.service.FTPService;
import com.service.XSSFService;

@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	XSSFService xssfService;

	@Autowired
	FTPService service;

	@Override
	public void run(String... args) throws Exception {
		// service.exportJSON();
		// xssfService.addNew();
	}

}

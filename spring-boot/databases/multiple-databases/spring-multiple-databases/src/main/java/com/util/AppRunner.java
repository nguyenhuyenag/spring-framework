package com.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.service.EntityManagerService;

@Component
public class AppRunner implements CommandLineRunner {

	@Autowired
	EntityManagerService service;

	// @Autowired
	// JdbcTemplateService service;

	@Override
	public void run(String... args) throws Exception {
		// service.findAll();
		service.showDataSourceURL();
	}

}

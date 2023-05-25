package com.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.service.EntityManagerService;

@Component
public class AppRunner implements CommandLineRunner {

	@Autowired
	EntityManagerService service;

	@Override
	public void run(String... args) throws Exception {
//		List<Office> findAll = service.findAll();
//		if (!findAll.isEmpty()) {
//			findAll.forEach(t -> System.out.println(t.toString()));
//		}
		service.showDataSourceURL();
	}

}

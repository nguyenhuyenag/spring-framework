package com.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.repository.TestRepository;

@Component
public class AppRunner implements CommandLineRunner {

	@Autowired
	TestRepository testRepository;
	
	@Autowired
	TestOneToOne testOneToOne;

	@Override
	public void run(String... args) throws Exception {
		testOneToOne.test();
	}

	

}

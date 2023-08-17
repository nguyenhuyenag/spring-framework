package com.test;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.entity.createupdatetime.Customer;
import com.repository.CustomerRepository;

@Component
public class AppRunner implements CommandLineRunner {

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public void run(String... args) throws Exception {
		testHibernateTimestamp();
	}

	public void testHibernateTimestamp() throws InterruptedException {
		while (true) {
			String name = RandomStringUtils.randomAlphabetic(4).toLowerCase();
			String email = name + "@" + name + ".com";
			Customer entity = new Customer(name, email);
			customerRepository.save(entity);
			TimeUnit.SECONDS.sleep(10);
		}
	}

}

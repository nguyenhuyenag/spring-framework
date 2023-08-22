package com.test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.entity.createupdatetime.Customer;
import com.repository.JpaDateTimeRepository;
import com.repository.JpaFindFirstOrTopRepository;

@Component
public class AppRunner implements CommandLineRunner {

	@Autowired
	JpaFindFirstOrTopRepository findFirstOrTopRepository;
	
	@Autowired
	JpaDateTimeRepository jpaDateTimeRepository;

	@Override
	public void run(String... args) throws Exception {
		// testUpdateTimestamp();
		// testCreationTimestamp();
		Object date = jpaDateTimeRepository.getAsDateSQL();
		System.out.println(date);
	}

	public void testCreationTimestamp() throws InterruptedException {
		while (true) {
			String name = RandomStringUtils.randomAlphabetic(4).toLowerCase();
			String email = name + "@" + name + ".com";
			Customer entity = new Customer(name, email);
			findFirstOrTopRepository.save(entity);
			TimeUnit.SECONDS.sleep(1);
		}
	}

	public void testUpdateTimestamp() throws InterruptedException {
		while (true) {
			List<Customer> listCustomers = findFirstOrTopRepository.findAll();
			listCustomers.forEach(c -> {
				String newName = RandomStringUtils.randomAlphabetic(4).toLowerCase();
				c.setName(newName);
				c.setCreatedAt(LocalDateTime.now()); // Test update
				findFirstOrTopRepository.save(c);
			});
			TimeUnit.SECONDS.sleep(5);
		}
	}

}

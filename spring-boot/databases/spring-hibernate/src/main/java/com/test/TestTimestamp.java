package com.test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.entity.createupdatetime.Customer;
import com.repository.JpaDateTimeRepository;
import com.repository.JpaFindFirstOrTopRepository;

@Component
public class TestTimestamp {

	@Autowired
	JpaFindFirstOrTopRepository findFirstOrTopRepository;

	@Autowired
	JpaDateTimeRepository jpaDateTimeRepository;

	public void creationTimestamp() throws InterruptedException {
		while (true) {
			String name = RandomStringUtils.randomAlphabetic(4).toLowerCase();
			String email = name + "@" + name + ".com";
			Customer entity = new Customer(name, email);
			findFirstOrTopRepository.save(entity);
			TimeUnit.SECONDS.sleep(2);
		}
	}

	public void updateTimestamp() throws InterruptedException {
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

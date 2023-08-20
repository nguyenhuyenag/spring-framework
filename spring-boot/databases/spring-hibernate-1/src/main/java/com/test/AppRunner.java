package com.test;

import java.time.LocalDateTime;
import java.util.List;
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
		// testCreationTimestamp();
		// testUpdateTimestamp();
		String name = "zsid";
		// Customer customer = customerRepository.findFirstByNameLike(name);
		List<Customer> customer = customerRepository.findTop2ByNameOrderByIdDesc(name);
		if (customer != null) {
			System.out.println(customer);
		} else {
			System.out.println("Not found");
		}
	}

	public void testCreationTimestamp() throws InterruptedException {
		while (true) {
			String name = RandomStringUtils.randomAlphabetic(4).toLowerCase();
			String email = name + "@" + name + ".com";
			Customer entity = new Customer(name, email);
			customerRepository.save(entity);
			TimeUnit.SECONDS.sleep(1);
		}
	}

	public void testUpdateTimestamp() throws InterruptedException {
		while (true) {
			List<Customer> listCustomers = customerRepository.findAll();
			listCustomers.forEach(c -> {
				String newName = RandomStringUtils.randomAlphabetic(4).toLowerCase();
				c.setName(newName);
				c.setCreatedAt(LocalDateTime.now()); // Test update
				customerRepository.save(c);
			});
			TimeUnit.SECONDS.sleep(5);
		}
	}

}

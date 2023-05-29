package com;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.entity.User;
import com.entity.UserRole;
import com.repository.UserRepository;

@SpringBootApplication
public class BootApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class, args);
	}

	@Autowired
	private UserRepository userRepository;

	@Transactional
	public void test() {
		User user = userRepository.findById(1).get();
		// Hibernate.initialize(user.getUserRoles());
		Set<UserRole> userRoles = user.getUserRoles();
		System.out.println(userRoles.toString());
	}

	@Override
	public void run(String... args) throws Exception {
		// test();
	}

}

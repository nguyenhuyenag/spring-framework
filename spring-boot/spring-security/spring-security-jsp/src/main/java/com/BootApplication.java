package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.service.impl.UserService;

@SpringBootApplication
public class BootApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class, args);
	}

	@Autowired
	UserService userService;

	@Override
	public void run(String... args) throws Exception {
//		List<String> rolesByUserId = userService.getRolesByUserId(1);
//		if (rolesByUserId != null) {
//			for (String role : rolesByUserId) {
//				System.out.println(role);
//			}
//		}
	}

}

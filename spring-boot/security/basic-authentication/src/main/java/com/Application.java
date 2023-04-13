package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Application implements CommandLineRunner {

	// JAR
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Autowired
	PasswordEncoder encoder;

	@Override
	public void run(String... args) throws Exception {
		// PasswordEncoder encode = new BCryptPasswordEncoder();
		// System.out.println(encoder.encode("password"));
	}

}

package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BasicAuthApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BasicAuthApplication.class, args);
	}

	@Autowired
	PasswordEncoder encoder;

	@Override
	public void run(String... args) throws Exception {
		// Base64Utils.base64Authen("user", "123456");
		// String encodeToString = encoder.encode("123456");
		// System.out.println(encodeToString);
	}

}

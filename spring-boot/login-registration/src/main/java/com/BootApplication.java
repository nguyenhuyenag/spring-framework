package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BootApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class, args);
	}
	
	@Autowired
	PasswordEncoder encoder;

	@Override
	public void run(String... args) throws Exception {
		System.out.println(encoder.encode("123"));
		// System.out.println(MD5Encoder.encode("123".getBytes()));
		System.out.println(encoder.matches("123", "e10adc3949ba59abbe56e057f20f883e"));
	}

}

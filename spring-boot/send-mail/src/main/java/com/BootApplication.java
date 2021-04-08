package com;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mail.MailService;

@SpringBootApplication
public class BootApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// MailService.toOne("huyen.nv@ts24corp.com");
		MailService.toMany("huyen.nv@ts24corp.com, nguyenhuyenag@gmail.com");
	}

}

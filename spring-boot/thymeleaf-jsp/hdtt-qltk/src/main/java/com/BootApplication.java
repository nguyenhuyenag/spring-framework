package com;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.entity.User;
import com.repository.UserRepository;

@SpringBootApplication
public class BootApplication extends SpringBootServletInitializer implements CommandLineRunner {

	/* JAR */
	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class, args);
	}

	/* WAR */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BootApplication.class);
	}

	@Autowired
	UserRepository repository;

	public void init() {
		try {
			for (int i = 0; i < 99; i++) {
				User entity = new User();
				int id = Integer.parseInt(RandomStringUtils.randomNumeric(2));
				entity.setEmail("test" + id + "@ts24.com.vn");
				entity.setPassword("$2a$10$SEXHMyTixU6WMpEjd2YRqexXGtjeohEEmfhlgt361OzG4ZijJKo0a");
				entity.setMstTcgp("123456789");
				entity.setFullname("DỊCH VỤ IXHD TS24");
				entity.setActive(id % 2 == 0 ? 0 : 1);
				repository.save(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run(String... args) throws Exception {
		// init();
	}

}

package com;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication

public class Application extends SpringBootServletInitializer implements CommandLineRunner {

	/* WAR */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	// @Autowired
	// HoaDonRepository repo;

	@Override
	public void run(String... args) throws Exception {
		// repo.findAll().forEach(t -> System.out.println(t.getMaSoThueNBAN()));
//		PasswordEncoder endcoder = new BCryptPasswordEncoder();
//		System.out.println("123456: " + endcoder.encode("123456"));
		// System.out.println("TEST_CONFIG_PROPS: " + PathUtils.URL_WEBAPP);
	}

}

package com;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

@SpringBootApplication
public class MVCApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MVCApplication.class, args);
	}
	
	@Secured("ROLE_ADMIN")
	public void getUsername() {
	    SecurityContext securityContext = SecurityContextHolder.getContext();
	    String name = securityContext.getAuthentication().getName();
	    System.out.println(name);
	}

	@Override
	public void run(String... args) throws Exception {
		// PasswordEncoder p = new BCryptPasswordEncoder();
		// System.out.println(p.encode("123456"));
		// System.out.println(TimeUnit.HOURS.toMillis(1));
	}

}

package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.service.UserService;

@SpringBootApplication
public class Application extends SpringBootServletInitializer implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	@Autowired
	UserService userService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public void run(String... params) throws Exception {

//		Role roleAdmin = new Role();
//		roleAdmin.setName(RoleTypes.ROLE_ADMIN);
//		Set<Role> listRoleAdmin = new HashSet<>();
//		listRoleAdmin.add(roleAdmin);
//		User admin = new User(null, "admin", "admin", "admin@email.com", listRoleAdmin);
//		userService.signup(admin);
//
//		Role roleUser = new Role();
//		roleUser.setName(RoleTypes.ROLE_USER);
//		Set<Role> listRoleUser = new HashSet<>();
//		listRoleUser.add(roleUser);
//		User client = new User(null, "client", "client", "client@email.com", listRoleUser);
//		userService.signup(client);
//		
//		Role roleUser2 = new Role();
//		roleUser2.setName(RoleTypes.ROLE_USER);
//		Set<Role> listRoleUser2 = new HashSet<>();
//		listRoleUser2.add(roleUser2);
//		User client2 = new User(null, "client2", "client2", "client@email.com", listRoleUser2);
//		userService.signup(client2);

	}

}

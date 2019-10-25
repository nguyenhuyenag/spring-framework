package jwt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.security.crypto.password.PasswordEncoder;

import jwt.entity.Role;
import jwt.entity.User;
import jwt.enums.RoleTypes;
import jwt.service.UserService;

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

	Role role = new Role();
	List<Role> list = new ArrayList<>();

	@Override
	public void run(String... params) throws Exception {
		User admin = new User();
		admin.setUsername("admin");
		admin.setPassword("admin");
		System.out.println(passwordEncoder.encode("admin"));
		admin.setEmail("admin@email.com");
		role.setName(RoleTypes.ROLE_ADMIN);
		list.add(role);
		admin.setListRoles(list);
		userService.signup(admin);

		User client = new User();
		client.setUsername("client");
		client.setPassword("client");
		list.remove(role);
		role.setName(RoleTypes.ROLE_USER);
		list.add(role);
		System.out.println(passwordEncoder.encode("client"));
		client.setEmail("client@email.com");
		client.setListRoles(list);
		userService.signup(client);
	}

}

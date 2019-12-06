package com;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.repository.UserRepository;

@SpringBootApplication
public class Application extends SpringBootServletInitializer implements CommandLineRunner {

	// JAR
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	// WAR
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	@Autowired
	UserRepository repository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	private RedisTemplate<String, String> template;

	public void redisKeyValue() {
		template.opsForValue().set("java", "hello world");
		System.out.println("Value of key `java`: " + template.opsForValue().get("java"));
		template.delete("java");
		System.out.println("Value of key `loda`: " + template.opsForValue().get("java"));
	}

	public void redisList() {
		List<String> list = new ArrayList<>();
		list.add("Hello");
		list.add("redis");
		template.opsForList().rightPushAll("loda_list", list);
		System.out.println("Size of key loda: " + template.opsForList().size("loda_list"));
	}

	@Override
	public void run(String... args) throws Exception {
		// redisKeyValue();
	}

}

package com;

// import org.springframework.beans.BeanUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.entity.User;

@SpringBootApplication
public class Application implements CommandLineRunner {

	private static ConfigurableApplicationContext context;

	public static void main(String[] args) {
		context = SpringApplication.run(Application.class, args);
	}

	public static void restart() {
		Thread thread = new Thread(() -> {
			context.close();
			context = SpringApplication.run(Application.class, "--spring.profiles.active=your_profile");
		});
		thread.setDaemon(false);
		thread.start();
	}

	@Override
	public void run(String... args) throws Exception {
		User source = new User();
		source.setId(1);
		source.setName("HuyenNV");
		source.setEmail("huyennv@gmail.com");
		User target = new User();
		org.springframework.beans.BeanUtils.copyProperties(source, target);
		// org.apache.commons.beanutils.BeanUtils.copyProperties(target, source);
		System.out.println("u1: " + source);
		System.out.println("u2: " + target);
	}

}

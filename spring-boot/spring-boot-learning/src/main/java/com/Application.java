package com;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.boot.component.Hat;
import com.boot.component.autowired.GirlFriend;

@SpringBootApplication
@EnableAutoConfiguration
public class Application implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		// PageableJPA.init();
		// PageableJPA.info();
		// PageableJPA.sortPage();
		// PageableJPA.showAllPage();
		// userRepository.getAllEmails().forEach(t->System.out.println(t));
		// System.out.println(userRepository.getAllEmails());
	}

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);
		GirlFriend g = ctx.getBean(GirlFriend.class);
		System.out.println(g);
		System.out.println(g.outfit);
		g.outfit.wear();
	}

}

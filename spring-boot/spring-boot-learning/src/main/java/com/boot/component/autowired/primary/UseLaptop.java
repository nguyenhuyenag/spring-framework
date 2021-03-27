package com.boot.component.autowired.primary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ApplicationContext ctx = SpringApplication.run(Application.class, args);
 * UseLaptop laptop = ctx.getBean(UseLaptop.class);
 * laptop.use();
 *
 */
@Component
public class UseLaptop {

	@Autowired
	private Laptop laptop;

	public void use() {
		laptop.start();
		System.out.println("Begin use Laptop");
	}

}

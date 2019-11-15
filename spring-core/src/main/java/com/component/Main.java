package com.component;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		try (AbstractApplicationContext context = new ClassPathXmlApplicationContext("scan/context.xml");) {
			UserService userService = (UserService) context.getBean("userService");
			userService.findUser(1);
		}
	}

}

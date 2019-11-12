package com.di;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		// FileSystemXmlApplicationContext("classpath:di/context.xml");
		try (AbstractApplicationContext context = new ClassPathXmlApplicationContext("di/context.xml");) {
			HelloWorld helloWorld1 = (HelloWorld) context.getBean("helloWorld1");
			helloWorld1.print();
			HelloWorld helloWorld2 = (HelloWorld) context.getBean("helloWorld2");
			helloWorld2.print();
		}
	}

}

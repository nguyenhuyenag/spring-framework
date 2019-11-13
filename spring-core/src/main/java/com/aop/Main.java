package com.aop;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) throws Exception {
		try (AbstractApplicationContext context = new ClassPathXmlApplicationContext("aop/aop-context.xml");) {
			Hello hello = (Hello) context.getBean("hello");
			hello.method1();
			System.out.println("\n");
			hello.method2();
			System.out.println("\n");
			hello.method3();
		}
	}

}

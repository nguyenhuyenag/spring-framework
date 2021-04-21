package com.di.collection;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		try (AbstractApplicationContext context = new ClassPathXmlApplicationContext("di/collection-context.xml");) {
			Person p1 = (Person) context.getBean("person1");
			p1.print();
			Person p2 = (Person) context.getBean("person2");
			p2.print();
		}
	}

}

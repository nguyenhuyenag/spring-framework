package com.property;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		try (AbstractApplicationContext context = new ClassPathXmlApplicationContext("property/context.xml");) {
			DataResource data = (DataResource) context.getBean("dataResource");
			data.printConnection();
		}
	}

}

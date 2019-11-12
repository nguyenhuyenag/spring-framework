package com.ioc.context;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		// ApplicationContext context = new
		// ClassPathXmlApplicationContext("application-context.xml");
		// DataResource obj = (DataResource) context.getBean("dataResource");
		// obj.printConnection();
		// ((ClassPathXmlApplicationContext) context).close();

		try (AbstractApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");) {
			// ClassPathXmlApplicationContext(new String[]{"classpath*:*.xml" }))
			DataResource obj = (DataResource) context.getBean("dataResource");
			obj.printConnection();
		}
	}

}

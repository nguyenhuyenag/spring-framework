package com.ioc.beanfactory;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

public class Main {

	public static void main(String[] args) {
		// tạo factory
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		// đọc thông tin file cấu hình và gán vào factory
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		reader.loadBeanDefinitions(new ClassPathResource("bean.xml"));
		// tạo đối tượng từ factory
		HelloWorld obj = (HelloWorld) factory.getBean("helloworld"); // id
		obj.getMessage();
	}

}

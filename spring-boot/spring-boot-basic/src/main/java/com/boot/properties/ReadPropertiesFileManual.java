package com.boot.properties;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;

public class ReadPropertiesFileManual {
	
	public static void main(String[] args) throws IOException {
		PropertiesFactoryBean factory = new PropertiesFactoryBean();
		
		// doc tu file
		factory.setLocation(new ClassPathResource("application.properties"));
		
		// them thu cong
		Properties pro =  new Properties();
		pro.put("mykey", "my_value");
		factory.setProperties(pro);
		factory.afterPropertiesSet();
		
		// System.out.println(factory.getObject().toString());
		
		// convert -> HashMap
		HashMap<String, Object> map = new HashMap<>();
		factory.getObject().entrySet().forEach(e -> {
			map.put(String.valueOf(e.getKey()), e.getValue());
		});
		System.out.println(map);
	}
	
}

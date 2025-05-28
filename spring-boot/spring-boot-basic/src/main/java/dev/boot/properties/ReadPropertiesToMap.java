package dev.boot.properties;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Properties;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;

public class ReadPropertiesToMap {
	
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
		Objects.requireNonNull(factory.getObject()).forEach((key, value) -> map.put(String.valueOf(key), value));
		System.out.println(map);
	}
	
}

package com.spring.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:log4j.properties")
@PropertySource("classpath:config.properties")
@PropertySource("classpath:application.properties")
public class PropertiesReader {

	public static List<String> FILE_ACCEPT;
	public static String SPRING_DATASOURCE_URL;
	public static String SPRING_DATASOURCE_USERNAME;
	public static String SPRING_DATASOURCE_PASSWORD;
	public static String SPRING_DATASOURCE_DRIVERCLASSNAME;

	@Value("${FILE_ACCEPT}")
	private void setFileAccept(String fileAccept) {
		PropertiesReader.FILE_ACCEPT = Stream.of(fileAccept.split(",")).map(String::trim).collect(Collectors.toList());
	}

	@Value("${spring.datasource.url}")
	private void setSpringDatasourceUrl(String SPRING_DATASOURCE_URL) {
		PropertiesReader.SPRING_DATASOURCE_URL = SPRING_DATASOURCE_URL;
	}

	@Value("${spring.datasource.username}")
	private void setSpringDatasourceUsername(String SPRING_DATASOURCE_USERNAME) {
		PropertiesReader.SPRING_DATASOURCE_USERNAME = SPRING_DATASOURCE_USERNAME;
	}
	
	@Value("${spring.datasource.password}")
	private void setSpringDatasourcePassword(String SPRING_DATASOURCE_PASSWORD) {
		PropertiesReader.SPRING_DATASOURCE_PASSWORD = SPRING_DATASOURCE_PASSWORD;
	}
	
	@Value("${spring.datasource.driver-class-name}")
	private void setSpringDatasourceDriverClassName(String SPRING_DATASOURCE_DRIVERCLASSNAME) {
		PropertiesReader.SPRING_DATASOURCE_DRIVERCLASSNAME = SPRING_DATASOURCE_DRIVERCLASSNAME;
	}
	
}

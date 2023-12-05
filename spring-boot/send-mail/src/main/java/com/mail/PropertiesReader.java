package com.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:application.properties", encoding = "utf-8")
public class PropertiesReader {

	public static String MAIL_USERNAME;
	// public static String MAIL_PASSWORD;

	@Value("${spring.mail.username}")
	private void setMailUsername(String username) {
		PropertiesReader.MAIL_USERNAME = username;
	}

//	@Value("${spring.mail.password}")
//	private void setMailPassword(String pwd) {
//		PropertiesReader.MAIL_PASSWORD = pwd;
//	}

}

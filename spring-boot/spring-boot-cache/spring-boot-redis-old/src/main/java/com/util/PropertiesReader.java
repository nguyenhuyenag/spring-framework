package com.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config.properties")
@PropertySource("classpath:application.properties")
public class PropertiesReader {

	public static long JWT_EXPIRATION_TIME;

	@Value("${jwt.expiration.time}")
	private void setJwtExpirationTime(long time) {
		PropertiesReader.JWT_EXPIRATION_TIME = time;
	}

}

package com.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:config.properties")
public class ConfigReader {

	public static String SMS_URL;
	public static String SMS_USERNAME;
	public static String SMS_PASSWORD;
	public static String SMS_BRANDNAME;
	public static String SMS_KEY;

	@Value("${sms.url}")
	private void setSMSUrl(String url) {
		ConfigReader.SMS_URL = url;
	}
	
	@Value("${sms.username}")
	private void setSMSUsername(String username) {
		ConfigReader.SMS_USERNAME = username;
	}

	@Value("${sms.password}")
	private void setSMSPassword(String password) {
		ConfigReader.SMS_PASSWORD = password;
	}

	@Value("${sms.brandname}")
	private void setSMSBrandname(String brandname) {
		ConfigReader.SMS_BRANDNAME = brandname;
	}
	
	@Value("${sms.key}")
	private void setSMSKey(String key) {
		ConfigReader.SMS_KEY = key;
	}

}

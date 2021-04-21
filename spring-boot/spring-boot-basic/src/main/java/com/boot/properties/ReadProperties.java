package com.boot.properties;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration // this is @Component
@PropertySource(value = "classpath:basic.properties", encoding = "utf-8")
public class ReadProperties {

	public static String URL;
	public static String LANGUAGE;
	public static List<String> VERSION;

	@Value("${url}") // <= dùng chữ thường
	void setUrl(String url) {
		ReadProperties.URL = url;
	}

	@Value("${version}")
	private void setVersion(List<String> version) {
		ReadProperties.VERSION = version;
	}

	@Value("${language}")
	// không thể dùng static
	public static void setLanguage(String language) {
		ReadProperties.LANGUAGE = language;
	}
	
}

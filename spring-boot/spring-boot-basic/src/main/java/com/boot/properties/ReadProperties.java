package com.boot.properties;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration // <- This is @Component
@PropertySource(value = "classpath:basic.properties", encoding = "utf-8")
public class ReadProperties {

	public String URL;
	public String LANGUAGE;
	public List<String> VERSION;

	@Value("${url}") // <= dùng chữ thường
	void setUrl(String url) {
		this.URL = url;
	}

	@Value("${version}")
	private void setVersion(List<String> version) {
		this.VERSION = version;
	}

	// Không thể dùng static
	@Value("${language}")
	public void setLanguage(String language) {
		this.LANGUAGE = language;
	}

}

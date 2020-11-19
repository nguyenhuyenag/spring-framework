package com.boot.properties;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:config.properties")
public class Config {

	@Value("${config.url}")
	private String url;

	@Value("${config.version}")
	private List<String> version;

	public String getUrl() {
		return url;
	}

	public List<String> getVersion() {
		return version;
	}

}

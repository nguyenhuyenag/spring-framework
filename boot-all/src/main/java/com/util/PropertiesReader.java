package com.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:log4j.properties")
@PropertySource("classpath:application.properties")
public class PropertiesReader {

	private String serverPort;

	@Value("${server.port}")
	private void setServerPort(String serverPort) {
		this.serverPort = serverPort;
	}

	public String getServerPort() {
		return serverPort;
	}

}

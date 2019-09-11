package com.boot.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class ApplicationProperties {

	private String serverPort;

	@Value("${server.port}")
	private void setServerPort(String serverPort) {
		this.serverPort = serverPort;
	}

	public String getServerPort() {
		return serverPort;
	}

}

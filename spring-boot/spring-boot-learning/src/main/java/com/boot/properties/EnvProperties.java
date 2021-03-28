package com.boot.properties;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class EnvProperties implements EnvironmentAware {

	private static Environment env;
	
	@Override
    public void setEnvironment(final Environment environment) {
		EnvProperties.env = environment;
    }

	public String getValue() {
		return env.getProperty("url");
	}

}

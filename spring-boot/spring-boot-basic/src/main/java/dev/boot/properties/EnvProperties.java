package dev.boot.properties;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class EnvProperties implements EnvironmentAware {

	private static Environment environment;

	@Override
	public void setEnvironment(final Environment environment) {
		EnvProperties.environment = environment;
	}

	// How to use? -> EnvProperties.getUrl()
	public static String getUrl() {
		return environment.getProperty("url");
	}

}

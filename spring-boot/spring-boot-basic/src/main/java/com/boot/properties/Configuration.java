package com.boot.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;

@Service
@Getter
@Setter
@ConfigurationProperties()
@PropertySource("classpath:basic.properties")
public class Configuration {

	/**
	 * How to use? -> @Autowired Configuration configuration;
	 */
	private String version;
	private String url;
	private String language;

}

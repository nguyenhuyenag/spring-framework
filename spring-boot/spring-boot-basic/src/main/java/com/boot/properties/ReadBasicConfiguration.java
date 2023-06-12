package com.boot.properties;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;

@Service
@Getter
@Setter
@ConfigurationProperties
@PropertySource("classpath:basic.properties")
public class ReadBasicConfiguration {

	/*-
	 * How to use? -> 	@Autowired 
	 * 					ReadBasicConfiguration configuration;
	 */
	private List<String> version;
	private String url;
	private String language;

}

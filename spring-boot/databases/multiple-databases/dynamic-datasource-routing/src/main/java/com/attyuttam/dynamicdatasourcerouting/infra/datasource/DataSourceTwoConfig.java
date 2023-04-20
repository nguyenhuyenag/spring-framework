package com.attyuttam.dynamicdatasourcerouting.infra.datasource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Configuration
@PropertySource("classpath:database-2.properties")
public class DataSourceTwoConfig {

	@Value("${url}")
	private String url;

	@Value("${tusername}")
	private String username;

	@Value("${password}")
	private String password;

}

package com.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DataSourceConfig {

	@Bean
	@Primary
	@ConfigurationProperties("spring.datasource")
	public DataSourceProperties dataSourceProperties1() {
		return new DataSourceProperties();
	}

	@Bean
	@ConfigurationProperties("spring.second-datasource")
	public DataSourceProperties dataSourceProperties2() {
		return new DataSourceProperties();
	}

	@Bean
	@Primary
	public DataSource dataSource1() {
		return dataSourceProperties1() //
				.initializeDataSourceBuilder() //
				.build();
	}

	@Bean(name = "dataSource2")
	public DataSource dataSource2() {
		return dataSourceProperties2() //
				.initializeDataSourceBuilder() //
				.build();
	}

}

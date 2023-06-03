package com.boot.datasource;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class CreateDataSource {

	String url, username, password, driverClassName;

	@Bean
	public DataSource data1Source() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource(url, username, password);
		dataSource.setDriverClassName(driverClassName);
		return dataSource;
	}

	@Bean
	public DataSource data2Source() {
		return DataSourceBuilder.create() //
				.url(url) //
				.username(username) //
				.password(password) //
				.driverClassName(driverClassName) //
				.build();
	}

}

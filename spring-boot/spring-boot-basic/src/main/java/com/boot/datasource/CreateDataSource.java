package com.boot.datasource;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

// @Configuration (tạm đóng, mở ra chạy sẽ lỗi do chưa cấu hình EntityManagerFactory)
public class CreateDataSource {

	private String url, username, password;

	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;

	@Bean(name = "data1Source")
	public DataSource data1Source() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource(url, username, password);
		dataSource.setDriverClassName(driverClassName);
		return dataSource;
	}

	@Bean(name = "data2Source")
	public DataSource data2Source() {
		return DataSourceBuilder.create() //
				.url(url) //
				.username(username) //
				.password(password) //
				.driverClassName(driverClassName) //
				.build();
	}

}

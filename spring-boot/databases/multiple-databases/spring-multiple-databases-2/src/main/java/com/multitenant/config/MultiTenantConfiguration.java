package com.multitenant.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MultiTenantConfiguration {

	private final String DEFAULT_TENANT = "tenant_1";

	@Bean
	public DataSource dataSource() {
		File[] listFiles = Paths.get("allTenants").toFile().listFiles();
		Map<Object, Object> targetDataSources = new HashMap<>();
		for (File file : listFiles) {
			try (FileInputStream fis = new FileInputStream(file)) {
				Properties properties = new Properties();
				properties.load(fis);
				DataSourceBuilder<?> builder = DataSourceBuilder.create();
				builder.url(properties.getProperty("datasource.url"));
				builder.username(properties.getProperty("datasource.username"));
				builder.password(properties.getProperty("datasource.password"));
				builder.driverClassName(properties.getProperty("datasource.driver-class-name"));
				String tenantName = properties.getProperty("name");
				targetDataSources.put(tenantName, builder.build());
			} catch (IOException e) {
				throw new RuntimeException("Problem in tenant datasource:" + e);
			}
		}
		MultiTenantDataSource dataSources = new MultiTenantDataSource();
		dataSources.setDefaultTargetDataSource(targetDataSources.get(DEFAULT_TENANT));
		dataSources.setTargetDataSources(targetDataSources);
		dataSources.afterPropertiesSet();
		return dataSources;
	}

}
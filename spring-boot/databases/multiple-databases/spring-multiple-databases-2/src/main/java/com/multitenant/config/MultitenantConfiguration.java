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
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

@Configuration
public class MultitenantConfiguration {

	// @Value("")
	private String defaultTenant = "tenant_1";

	@Bean
	// @ConfigurationProperties(prefix = "tenants")
	public DataSource dataSource() {
		File[] files = Paths.get("allTenants").toFile().listFiles();
		Map<Object, Object> dataSources = new HashMap<>();
		for (File propertyFile : files) {
			DataSourceBuilder<?> dataSource = DataSourceBuilder.create();
			try {
				Properties tenantProperties = new Properties();
				tenantProperties.load(new FileInputStream(propertyFile));
				String tenantId = tenantProperties.getProperty("name");
				dataSource.url(tenantProperties.getProperty("datasource.url"));
				dataSource.username(tenantProperties.getProperty("datasource.username"));
				dataSource.password(tenantProperties.getProperty("datasource.password"));
				dataSource.driverClassName(tenantProperties.getProperty("datasource.driver-class-name"));
				dataSources.put(tenantId, dataSource.build());
			} catch (IOException e) {
				throw new RuntimeException("Problem in tenant datasource:" + e);
			}
		}
		AbstractRoutingDataSource dataSource = new MultitenantDataSource();
		dataSource.setDefaultTargetDataSource(dataSources.get(defaultTenant));
		dataSource.setTargetDataSources(dataSources);
		dataSource.afterPropertiesSet();
		return dataSource;
	}

}
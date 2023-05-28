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
			DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
			try {
				Properties tenantProperties = new Properties();
				tenantProperties.load(new FileInputStream(propertyFile));
				String tenantId = tenantProperties.getProperty("name");
				dataSourceBuilder.url(tenantProperties.getProperty("datasource.url"));
				dataSourceBuilder.username(tenantProperties.getProperty("datasource.username"));
				dataSourceBuilder.password(tenantProperties.getProperty("datasource.password"));
				dataSourceBuilder.driverClassName(tenantProperties.getProperty("datasource.driver-class-name"));
				dataSources.put(tenantId, dataSourceBuilder.build());
			} catch (IOException exp) {
				throw new RuntimeException("Problem in tenant datasource:" + exp);
			}
		}
		AbstractRoutingDataSource dataSource = new MultitenantDataSource();
		dataSource.setDefaultTargetDataSource(dataSources.get(defaultTenant));
		dataSource.setTargetDataSources(dataSources);
		dataSource.afterPropertiesSet();
		return dataSource;
	}

}
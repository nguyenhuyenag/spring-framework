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
public class MultiTenantConfiguration {

	private String defaultTenant = "tenant_1";

	@Bean
	public DataSource dataSource() {
		File[] files = Paths.get("allTenants").toFile().listFiles();
		Map<Object, Object> targetDataSources = new HashMap<>();
		for (File propertyFile : files) {
			// DataSourceBuilder<?> builder = DataSourceBuilder.create();
			try (FileInputStream fis = new FileInputStream(propertyFile)) {
				Properties tenantProperties = new Properties();
				tenantProperties.load(fis);
				String tenantName = tenantProperties.getProperty("name");
				DataSourceBuilder<?> builder = DataSourceBuilder.create()
						.url(tenantProperties.getProperty("datasource.url"))
						.username(tenantProperties.getProperty("datasource.username"))
						.password(tenantProperties.getProperty("datasource.password"))
						.driverClassName(tenantProperties.getProperty("datasource.driver-class-name"));
				targetDataSources.put(tenantName, builder.build());
			} catch (IOException e) {
				throw new RuntimeException("Problem in tenant datasource:" + e);
			}
		}
		AbstractRoutingDataSource multitenantDataSource = new MultiTenantDataSource();
		multitenantDataSource.setDefaultTargetDataSource(targetDataSources.get(defaultTenant));
		multitenantDataSource.setTargetDataSources(targetDataSources);
		multitenantDataSource.afterPropertiesSet();
		return multitenantDataSource;
	}

//	@Bean
//	// @ConfigurationProperties(prefix = "tenants")
//	public DataSource dataSource() {
//		File[] files = Paths.get("allTenants").toFile().listFiles();
//		Map<Object, Object> targetDataSources = new HashMap<>();
//		for (File propertyFile : files) {
//			DataSourceBuilder<?> builder = DataSourceBuilder.create();
//			try {
//				Properties tenantProperties = new Properties();
//				tenantProperties.load(new FileInputStream(propertyFile));
//				String tenantId = tenantProperties.getProperty("name");
//				builder.url(tenantProperties.getProperty("datasource.url"));
//				builder.username(tenantProperties.getProperty("datasource.username"));
//				builder.password(tenantProperties.getProperty("datasource.password"));
//				builder.driverClassName(tenantProperties.getProperty("datasource.driver-class-name"));
//				targetDataSources.put(tenantId, builder.build());
//			} catch (IOException e) {
//				throw new RuntimeException("Problem in tenant datasource:" + e);
//			}
//		}
//		AbstractRoutingDataSource multitenantDataSource = new MultiTenantDataSource();
//		multitenantDataSource.setDefaultTargetDataSource(targetDataSources.get(defaultTenant));
//		multitenantDataSource.setTargetDataSources(targetDataSources);
//		multitenantDataSource.afterPropertiesSet();
//		return multitenantDataSource;
//	}

}
package com.multitenant;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

@Configuration
public class MultiTenantManager {

	private static final Logger LOG = LoggerFactory.getLogger(MultiTenantManager.class);

	@Value("${spring.datasource.url}")
	private String url;

	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;

	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;

	private final static ThreadLocal<String> currentTenant = new ThreadLocal<>();
	private final static Map<Object, Object> tenantDataSources = new ConcurrentHashMap<>();

	private static DataSourceProperties properties;
	private static AbstractRoutingDataSource multiTenantDataSource;

	public MultiTenantManager(DataSourceProperties properties) {
		MultiTenantManager.properties = properties;
	}

	private DriverManagerDataSource defaultDataSource() {
		DriverManagerDataSource defaultDataSource = new DriverManagerDataSource();
		defaultDataSource.setDriverClassName(driverClassName);
		defaultDataSource.setUrl(url);
		defaultDataSource.setUsername(username);
		defaultDataSource.setPassword(password);
		return defaultDataSource;
	}

	@Bean
	public DataSource dataSource() {
		multiTenantDataSource = new AbstractRoutingDataSource() {
			@Override
			protected Object determineCurrentLookupKey() {
				return currentTenant.get();
			}
		};
		multiTenantDataSource.setDefaultTargetDataSource(defaultDataSource());
		multiTenantDataSource.setTargetDataSources(tenantDataSources);
		multiTenantDataSource.afterPropertiesSet();
		return multiTenantDataSource;
	}

	private static void addTenant(String databasename, String username, String password) throws SQLException {
		String url = "jdbc:mysql://localhost:3306/" + databasename + "?useUnicode=true&characterEncoding=utf-8";
		DataSource dataSource = DataSourceBuilder.create() //
				.driverClassName(properties.getDriverClassName()) //
				.url(url) //
				.username(username) //
				.password(password) //
				.build();
		if (tenantDataSources.containsKey(databasename)) {
			LOG.info("Tenant '{}' exists", databasename);
		} else {
			// Check that new connection is 'live'. If not - throw exception
			try (Connection c = dataSource.getConnection()) {
				tenantDataSources.put(databasename, dataSource);
				multiTenantDataSource.afterPropertiesSet();
				LOG.info("Tenant '{}' added", databasename);
			}
		}
		currentTenant.set(databasename);
		LOG.info("Tenant '{}' set as current.", databasename);
	}

//	private static void setCurrentTenant(String databasename) throws SQLException {
//		currentTenant.set(databasename);
//		LOG.info("Tenant '{}' set as current.", databasename);
//	}

	public static boolean switchDatabase2(int db) {
		String newDatabasename = null;
		String username = "root";
		String password = "root";
		if (db == 1) {
			newDatabasename = "multitenant1";
		}
		if (db == 2) {
			newDatabasename = "multitenant2";
		}
		try {
			addTenant(newDatabasename, username, password);
			// setCurrentTenant(newDatabasename);
			return true;
		} catch (SQLException e) {
			LOG.info(e.getMessage());
		}
		return false;
	}

}

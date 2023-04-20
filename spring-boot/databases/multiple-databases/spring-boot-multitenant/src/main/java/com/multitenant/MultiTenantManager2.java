//package com.multitenant;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
//import javax.sql.DataSource;
//
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
//
//import com.controller.XHDController;
//
//@Configuration
//public class MultiTenantManager2 {
//
//	private static final Logger LOG = LoggerFactory.getLogger(XHDController.class);
//
//	@Value("${spring.datasource.url}")
//	private String URL;
//
//	@Value("${spring.datasource.username}")
//	private String USERNAME;
//
//	@Value("${spring.datasource.password}")
//	private String PASSWORD;
//
//	@Value("${spring.datasource.driver-class-name}")
//	private String DRIVER_CLASSNAME;
//
//	public static String latestTenant = "";
//	private final static ThreadLocal<String> currentTenant = new ThreadLocal<>();
//	private final static Map<Object, Object> tenantDataSources = new ConcurrentHashMap<>();
//
//	private static DataSourceProperties properties;
//	private static AbstractRoutingDataSource multiTenantDataSource;
//	// private static Function<String, DataSourceProperties> tenantResolver;
//
//	public MultiTenantManager2(DataSourceProperties properties) {
//		MultiTenantManager2.properties = properties;
//	}
//
//	private DriverManagerDataSource defaultDataSource() {
//		DriverManagerDataSource defaultDataSource = new DriverManagerDataSource();
//		defaultDataSource.setDriverClassName(DRIVER_CLASSNAME);
//		defaultDataSource.setUrl(URL);
//		defaultDataSource.setUsername(USERNAME);
//		defaultDataSource.setPassword(PASSWORD);
//		return defaultDataSource;
//	}
//
//	@Bean
//	public DataSource dataSource() {
//		multiTenantDataSource = new AbstractRoutingDataSource() {
//			@Override
//			protected Object determineCurrentLookupKey() {
//				LOG.info("CurrentTenant: {}", currentTenant.get());
//				return currentTenant.get();
//			}
//		};
//		multiTenantDataSource.setDefaultTargetDataSource(defaultDataSource());
//		multiTenantDataSource.setTargetDataSources(tenantDataSources);
//		multiTenantDataSource.afterPropertiesSet();
//		return multiTenantDataSource;
//	}
//
//	private static void addTenant(String databasename, String username, String password) throws SQLException {
//		String url = "jdbc:mysql://localhost:3306/" + databasename + "?useUnicode=true&characterEncoding=utf-8";
//		DataSource dataSource = DataSourceBuilder.create() //
//				.driverClassName(properties.getDriverClassName()) //
//				.url(url) //
//				.username(username) //
//				.password(password) //
//				.build();
//		// Check that new connection is 'live'. If not - throw exception
//		try (Connection c = dataSource.getConnection()) {
//			tenantDataSources.put(databasename, dataSource);
//			multiTenantDataSource.afterPropertiesSet();
//			latestTenant = databasename;
//			LOG.debug("Tenant '{}' added", databasename);
//		}
//	}
//
//	private static void setCurrentTenant(String databasename) throws SQLException {
//		currentTenant.set(databasename);
//		LOG.debug("Tenant '{}' set as current.", databasename);
//	}
//
//	public static boolean switchDatabase(int db) {
//		// LOG.info("Current Database: {}", latestTenant);
//		String newDatabasename = null;
//		String username = "root";
//		String password = "root";
//		if (db == 1) {
//			newDatabasename = "multitenant1";
//		}
//		if (db == 2) {
//			newDatabasename = "multitenant2";
//		}
//		try {
//			if (!latestTenant.equals(newDatabasename)) {
//				addTenant(newDatabasename, username, password);
//			} else {
//				LOG.info("Database don't change");
//				if (StringUtils.isEmpty(latestTenant)) {
//					latestTenant = newDatabasename;
//				}
//			}
//			setCurrentTenant(newDatabasename);
//			return true;
//		} catch (SQLException e) {
//			LOG.info(e.getMessage());
//		}
//		return false;
//	}
//	public static boolean switchDatabase2(int db) {
//		String newDatabasename = null;
//		String username = "root";
//		String password = "root";
//		if (db == 1) {
//			newDatabasename = "multitenant1";
//		}
//		if (db == 2) {
//			newDatabasename = "multitenant2";
//		}
//		try {
//			addTenant(newDatabasename, username, password);
//			setCurrentTenant(newDatabasename);
//			return true;
//		} catch (SQLException e) {
//			LOG.info(e.getMessage());
//		}
//		return false;
//	}
//
//}

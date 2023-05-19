package com.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@Configuration
public class AutoRunScriptSQL {
	
	@Bean
	// DataSourceInitializer dataSourceInitializer(@Qualifier("dataSource") final DataSource dataSource) {
	DataSourceInitializer dataSourceInitializer(final DataSource dataSource) {
	    ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
	    resourceDatabasePopulator.addScript(new ClassPathResource("jdbc/schema.sql"));
	    DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
	    dataSourceInitializer.setDataSource(dataSource);
	    dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
	    return dataSourceInitializer;
	}
	
}

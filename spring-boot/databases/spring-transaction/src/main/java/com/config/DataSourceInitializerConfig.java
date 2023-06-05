package com.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@Configuration
public class DataSourceInitializerConfig {

	@Bean
	public DataSourceInitializer dataSourceInitializer(final DataSource dataSource) {
		ResourceDatabasePopulator resourceDatabase = new ResourceDatabasePopulator();
		resourceDatabase.addScript(new ClassPathResource("database/data.sql"));
		DataSourceInitializer initializer = new DataSourceInitializer();
		initializer.setDataSource(dataSource);
		initializer.setDatabasePopulator(resourceDatabase);
		return initializer;
	}

}

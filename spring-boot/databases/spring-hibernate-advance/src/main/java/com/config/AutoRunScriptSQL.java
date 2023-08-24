package com.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@Configuration
public class AutoRunScriptSQL {
	
	private static final Logger LOG = LoggerFactory.getLogger(AutoRunScriptSQL.class);

	@Bean
	public DataSourceInitializer dataSourceInitializer(final DataSource dataSource) {
		ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
		databasePopulator.addScript(new ClassPathResource("jdbc/auto_run_schema.sql"));
		DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
		dataSourceInitializer.setDataSource(dataSource);
		dataSourceInitializer.setDatabasePopulator(databasePopulator);
		LOG.warn("Run SQL script");
		return dataSourceInitializer;
	}

}

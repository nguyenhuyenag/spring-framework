package com.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class JdbcTemplateConfig {
	
	@Primary
	@Bean(name = "jdbcTemplate")
	public JdbcTemplate jdbcTemplate(javax.sql.DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
	@Bean(name = "jdbcTemplate2")
	public JdbcTemplate jdbcTemplate2(@Qualifier("dataSource2") javax.sql.DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
}

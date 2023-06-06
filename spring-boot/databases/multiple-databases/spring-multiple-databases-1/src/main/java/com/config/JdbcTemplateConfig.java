package com.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import com.util.BeanName;

@Configuration
public class JdbcTemplateConfig {
	
	@Primary
	@Bean(name = BeanName.DB1_JDBCTEMPLATE)
	public JdbcTemplate jdbcTemplate(javax.sql.DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
	@Bean(name = BeanName.DB2_JDBCTEMPLATE)
	public JdbcTemplate jdbcTemplate2(@Qualifier(BeanName.DB2_DATASOURCE) javax.sql.DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
}

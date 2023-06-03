package com.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
// @EnableAutoConfiguration
@EnableTransactionManagement
@EnableJpaRepositories( //
	transactionManagerRef = "transactionManager", //
	entityManagerFactoryRef = "entityManagerFactory", //
	basePackages = { "com.primary.repository" } //
)
public class PrimaryDbConfig {

	@Autowired
	private JpaVendorAdapter jpaVendorAdapter;

	@Autowired
	private DataSource dataSource;
	
	@Primary
	@Bean(name = "jdbcTemplate")
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource);
	}
	
	@Primary
	@Bean(name = "entityManagerFactory")
	public EntityManagerFactory entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSource);
		emf.setJpaVendorAdapter(jpaVendorAdapter);
		emf.setPackagesToScan("com.primary.entity");	// package for entities
		// emf.setPersistenceUnitName("persistenceUnit"); 	// for EntityManager
		emf.afterPropertiesSet();
		return emf.getObject();
	}

	@Primary
	@Bean(name = "transactionManager")
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}
	
}

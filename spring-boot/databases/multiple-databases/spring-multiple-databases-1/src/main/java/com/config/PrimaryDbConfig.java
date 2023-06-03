package com.config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
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
@EnableAutoConfiguration
@EnableTransactionManagement
@EnableJpaRepositories( //
	transactionManagerRef = "transaction1Manager", //
	entityManagerFactoryRef = "entity1ManagerFactory", //
	basePackages = { "com.primary.repository" } //
)
public class PrimaryDbConfig {

	@Autowired
	JpaVendorAdapter jpaVendorAdapter;

	@Autowired
	DataSource data1Source;
	
	@Primary
	@Bean(name = "jdbc1Template")
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(data1Source);
	}

	@Primary
	@Bean(name = "entity1ManagerFactory")
	public EntityManagerFactory entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(data1Source);
		emf.setJpaVendorAdapter(jpaVendorAdapter);
		emf.setPackagesToScan("com.primary.entity"); 			// package for entities
		emf.setPersistenceUnitName("persistence1Unit"); 	// for EntityManager
		emf.afterPropertiesSet();
		return emf.getObject();
	}
	
	@Primary
	@Bean(name = "entity1Manager")
	public EntityManager entityManager() {
		return entityManagerFactory().createEntityManager();
	}

	@Primary
	@Bean(name = "transaction1Manager")
	public PlatformTransactionManager transactionManager() {
		return new JpaTransactionManager(entityManagerFactory());
	}
	
}

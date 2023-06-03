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
	transactionManagerRef = "tm", 				// (3)
	entityManagerFactoryRef = "emf", 			// (2)
	basePackages = { "com.primary.repository" } // (1)
)
public class PrimaryDbConfig {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private JpaVendorAdapter jpaVendorAdapter;
	
	private static final String ENTITY_PACKAGE		=	"com.primary.entity"; // (4)
	
	@Primary
	@Bean(name = "jdbcTemplate")
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource);
	}
	
	@Primary
	@Bean(name = "emf") // (2)
	public EntityManagerFactory entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSource);
		emf.setJpaVendorAdapter(jpaVendorAdapter);
		emf.setPackagesToScan(ENTITY_PACKAGE);
		emf.afterPropertiesSet();
		return emf.getObject();
	}

	@Primary
	@Bean(name = "tm") // (3)
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}
//	
//	@Autowired
//	@Primary
//	@Bean(name = "tm")
//	public DataSourceTransactionManager tm1() {
//		return new DataSourceTransactionManager(dataSource);
//	}
	
}

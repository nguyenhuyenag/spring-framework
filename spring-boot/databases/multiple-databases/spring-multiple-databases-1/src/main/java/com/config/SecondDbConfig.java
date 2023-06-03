package com.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
//@EnableAutoConfiguration
@EnableTransactionManagement
@EnableJpaRepositories( //
	transactionManagerRef = "tm2", 				// (3)
	entityManagerFactoryRef = "emf2", 			// (2)
	basePackages = { "com.second.repository" } 	// (1)
)
public class SecondDbConfig {

//	@Value("${spring.jpa.properties.hibernate.dialect}")
//	private String dialect;
	
	private static final String ENTITY_PACKAGE 			= "com.second.entity"; // (4)
	public static final String PERSISTENCE_UNIT_NAME	= "persistence2Unit";
	
	@Autowired
	private JpaVendorAdapter jpaVendorAdapter;

	@Autowired
	@Qualifier("dataSource2")
	private DataSource dataSource;
	
	@Bean(name = "jdbcTemplate2")
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource);
	}
	
	@Bean(name = "emf2") // (2)
	public EntityManagerFactory entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSource);
		emf.setJpaVendorAdapter(jpaVendorAdapter);
		emf.setPackagesToScan(ENTITY_PACKAGE);			// package for entities
		emf.setPersistenceUnitName(PERSISTENCE_UNIT_NAME);	// for EntityManager
		emf.afterPropertiesSet();
		return emf.getObject();
	}

	@Bean(name = "tm2") // (3)
	public PlatformTransactionManager transactionManager(@Qualifier("emf2") EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}

}

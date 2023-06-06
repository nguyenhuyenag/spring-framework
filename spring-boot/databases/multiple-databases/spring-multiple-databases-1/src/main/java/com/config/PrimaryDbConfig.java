package com.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.util.BeanName;

@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
@EnableJpaRepositories( //
	transactionManagerRef = BeanName.DB1_TRANSACTION_MANAGER,	  	// (3)
	entityManagerFactoryRef = BeanName.DB1_ENTITYMANAGER_FACTORY,	// (2)
	basePackages = { BeanName.DB1_PACKAGE_REPOSITORY } 			  	// (1)
)
public class PrimaryDbConfig {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private JpaVendorAdapter jpaVendorAdapter;
	
	@Primary
	@Bean(name = BeanName.DB1_ENTITYMANAGER_FACTORY) // (2)
	public EntityManagerFactory entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSource);
		emf.setJpaVendorAdapter(jpaVendorAdapter);
		emf.setPackagesToScan(BeanName.DB1_ENTITY_PACKAGE);	// package for entities
		emf.setPersistenceUnitName(BeanName.DB1_PERSISTENCE_UNIT_NAME); // for EntityManager
		emf.afterPropertiesSet();
		return emf.getObject();
	}

	@Primary
	@Bean(name = BeanName.DB1_TRANSACTION_MANAGER) // (3)
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}

}

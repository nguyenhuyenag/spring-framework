package com.config;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

import com.util.BeanName;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories( //
	transactionManagerRef = BeanName.DB2_TRANSACTION_MANAGER,
	entityManagerFactoryRef = BeanName.DB2_ENTITYMANAGER_FACTORY,
	basePackages = { BeanName.DB2_PACKAGE_REPOSITORY }
)
public class SecondDbConfig {

	@Autowired
	private JpaVendorAdapter jpaVendorAdapter;

	@Autowired
	@Qualifier(BeanName.DB2_DATASOURCE)
	private javax.sql.DataSource dataSource;
	
	@Bean(name = BeanName.DB2_ENTITYMANAGER_FACTORY)
	public EntityManagerFactory entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSource);
		emf.setJpaVendorAdapter(jpaVendorAdapter);
		emf.setPackagesToScan(BeanName.DB2_ENTITY_PACKAGE);
		emf.setPersistenceUnitName(BeanName.DB2_PERSISTENCE_UNIT_NAME);
		emf.afterPropertiesSet();
		return emf.getObject();
	}

	@Bean(name = BeanName.DB2_TRANSACTION_MANAGER)
	public PlatformTransactionManager transactionManager(@Qualifier(BeanName.DB2_ENTITYMANAGER_FACTORY) EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}
	
	@Bean(name = BeanName.DB2_TRANSACTION_TEMPLATE)
	public TransactionTemplate transactionTemplate(@Qualifier(BeanName.DB2_TRANSACTION_MANAGER) PlatformTransactionManager ptm) {
		return new TransactionTemplate(ptm);
	}

}

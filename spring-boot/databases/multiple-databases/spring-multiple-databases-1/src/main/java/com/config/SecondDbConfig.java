package com.config;

import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories( //
	transactionManagerRef = "transaction2Manager", //
	entityManagerFactoryRef = "entity2ManagerFactory", //
	basePackages = { "com.second.repository" } //
)
public class SecondDbConfig {

	@Autowired
	JpaVendorAdapter jpaVendorAdapter;

	@Value("${spring.second-datasource.url}")
	private String url;

	@Value("${spring.second-datasource.username}")
	private String username;

	@Value("${spring.second-datasource.password}")
	private String password;

	@Value("${spring.second-datasource.driver-class-name}")
	private String driverClassName;

	@Value("${spring.jpa.properties.hibernate.dialect}")
	private String dialect;

	public DataSource data2Source() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource(url, username, password);
		dataSource.setDriverClassName(driverClassName);
		return dataSource;
	}

	@Bean(name = "jdbc2Template")
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(data2Source());
	}

	@Bean(name = "entity2Manager")
	public EntityManager entityManager() {
		return entityManagerFactory().createEntityManager();
	}

	@Bean(name = "entity2ManagerFactory")
	public EntityManagerFactory entityManagerFactory() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", dialect);
		
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(data2Source());
		emf.setJpaVendorAdapter(jpaVendorAdapter);
		emf.setPackagesToScan("com.second.entity");
		emf.setPersistenceUnitName("persistence2Unit");
		emf.setJpaProperties(properties);
		emf.afterPropertiesSet();
		return emf.getObject();
	}

	@Bean(name = "transaction2Manager")
	public PlatformTransactionManager transactionManager() {
		return new JpaTransactionManager(entityManagerFactory());
	}

}

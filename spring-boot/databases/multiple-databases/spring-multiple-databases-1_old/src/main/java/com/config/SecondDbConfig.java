package com.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
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
	
	public static final String PERSISTENCE_UNIT_NAME = "persistence2Unit";

	@Bean//(name = "data2Source")
	@ConfigurationProperties("spring.datasource.todos")
	public DataSource dataSource() {
		return DataSourceBuilder.create() //
				.url(url) //
				.username(username) //
				.password(password) //
				.driverClassName(driverClassName) //
				.build();
	}

	@Bean(name = "jdbc2Template")
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}

	@Bean(name = "entity2ManagerFactory")
	public EntityManagerFactory entityManagerFactory() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", dialect);
		
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setJpaVendorAdapter(jpaVendorAdapter);
		emf.setDataSource(dataSource());
		emf.setPackagesToScan("com.second.entity");
		emf.setPersistenceUnitName(PERSISTENCE_UNIT_NAME);
		emf.setJpaProperties(properties);
		emf.afterPropertiesSet();
		return emf.getObject();
	}
	
//	@Bean(name = "entity2Manager")
//	public EntityManager entityManager(@Qualifier("entity2ManagerFactory") EntityManagerFactory emf) {
//		return emf.createEntityManager();
//	}
//
//	@Bean(name = "transaction2Manager")
//	public PlatformTransactionManager transactionManager( //
//			@Qualifier("entity2ManagerFactory") EntityManagerFactory emf) {
//		return new JpaTransactionManager(emf);
//	}

}

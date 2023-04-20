package com.attyuttam.dynamicdatasourcerouting.infra.datasource.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableJpaRepositories( //
	basePackages = "com.attyuttam.dynamicdatasourcerouting", //
	transactionManagerRef = "transcationManager", //
	entityManagerFactoryRef = "entityManager"//
)
@RequiredArgsConstructor
@EnableTransactionManagement
@DependsOn("dataSourceRouting")
public class DataSourceConfig {

	private final DataSourceRouting dataSourceRouting;

	@Bean
	@Primary
	public DataSource dataSource() {
		return dataSourceRouting;
	}

	@Bean(name = "entityManager")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(dataSource()) //
				.packages("com.attyuttam.dynamicdatasourcerouting.domain") //
				.build();
	}

	// @Autowired
	@Bean(name = "transcationManager")
	public JpaTransactionManager transactionManager(
			@Qualifier("entityManager") LocalContainerEntityManagerFactoryBean entityManager) {
		return new JpaTransactionManager(entityManager.getObject());
	}

}

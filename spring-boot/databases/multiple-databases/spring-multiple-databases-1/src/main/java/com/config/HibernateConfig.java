package com.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;

@EnableAutoConfiguration
public class HibernateConfig {

//	@Bean
//	public HibernateJpaSessionFactoryBean sessionFactory(EntityManagerFactory emf) {
//	    HibernateJpaSessionFactoryBean fact = new HibernateJpaSessionFactoryBean();
//	    fact.setEntityManagerFactory(emf);
//	    return fact;
//	}

	@Bean
	public HibernateJpaSessionFactoryBean sessionFactory() {
		return new HibernateJpaSessionFactoryBean();
	}

}

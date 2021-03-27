package com.boot.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*-
 * - 
 * 		ApplicationContext context = SpringApplication.run(Application.class, args);
 * 		// mysql
 * 		Connector mysql = (Connector) context.getBean("mysql");
 * 		mysql.connect();
 * 		// postgresql
 * 		Connector postgresql = (Connector) context.getBean("postgresql");
 * 		postgresql.connect();
 */
@Configuration
public class AppConfig {

	@Bean("mysql")
	Connector mysqlConfigure() {
		Connector mysql = new MySQL();
		mysql.setUrl("jdbc:mysql://localhost:3306/data");
		return mysql;
	}
	
	@Bean
	// Khởi tạo một instance của SimpleBean và trả ra ngoài
	SimpleBean simpleBeanConfigure() {
		return new SimpleBean("database_name");
	}

	@Bean("postgresql")
	Connector postgresqlConfigure(SimpleBean bean) { // Tự động inject SimpleBean
		Connector postgre = new PostgreSQL();
		postgre.setUrl("postgresql://localhost:3000/" + bean.getUsername());
		return postgre;
	}

}

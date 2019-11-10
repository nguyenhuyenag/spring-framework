package com.of.configuration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

	@Bean("mysql-connector")
	Connector mysqlConfigure() {
		Connector mysql = new MySQL();
		mysql.setUrl("jdbc:mysql://localhost:3306/data");
		// Set username, password, ...
		return mysql;
	}

	@Bean("mongodb-connector")
	Connector mongodbConfigure() {
		Connector mongo = new MongoDB();
		mongo.setUrl("mongodb://mongodb.com:27017/data");
		return mongo;
	}

	@Bean("postgresql-connector")
	Connector postgresqlConfigure(SimpleBean bean) { // Tự động inject SimpleBean
		Connector postgre = new PostgreSQL();
		postgre.setUrl("postgresql://localhost/data/" + bean.getUsername());
		return postgre;
	}

	@Bean
	SimpleBean simpleBeanConfigure() {
		// Khởi tạo một instance của SimpleBean và trả ra ngoài
		return new SimpleBean("Java");
	}

	@SuppressWarnings("null")
	public static void main() {

		ApplicationContext context = null;

		Connector mysql = (Connector) context.getBean("mysql-connector");
		mysql.connect();

		Connector mongodb = (Connector) context.getBean("mongodb-connector");
		mongodb.connect();

		Connector postgresql = (Connector) context.getBean("postgresql-connector");
		postgresql.connect();

	}

}

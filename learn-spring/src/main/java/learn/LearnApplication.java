package learn;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import learn.of.configuration.Connector;

@SpringBootApplication
public class LearnApplication implements CommandLineRunner {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(LearnApplication.class, args);

		Connector mysql = (Connector) context.getBean("mysql-connector");
		mysql.connect();

		Connector mongodb = (Connector) context.getBean("mongodb-connector");
		mongodb.connect();

		Connector postgresql = (Connector) context.getBean("postgresql-connector");
		postgresql.connect();

	}

	@Override
	public void run(String... args) throws Exception {

	}

}

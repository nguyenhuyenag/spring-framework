package core;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import core.entity.createupdatetime.Customer;
import core.repository.CustomerRepository;

@SpringBootApplication
public class Application extends SpringBootServletInitializer implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	@Autowired
	CustomerRepository repositoty;

	@Override
	public void run(String... args) throws Exception {

		// // insert
		// repositoty.save(new Customer("Kai", "Viet Nam"));
		// repositoty.save(new Customer("Thanos", "Viet Nam"));
		// repositoty.save(new Customer("Thor", "Asgard"));
		// repositoty.save(new Customer("Hulk", "USA"));
		// repositoty.save(new Customer("Iron Man", "USA"));

		// update
		Optional<Customer> entity = repositoty.findById(2);
		entity.ifPresent(t -> {
			t.setAddress("Titan");
			repositoty.save(t);
		});

	}

}

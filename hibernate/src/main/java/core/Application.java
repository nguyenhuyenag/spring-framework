package core;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import core.entity.cascade.Companys;
import core.entity.cascade.Employees;
import core.repository.CompanysRepositoty;

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
	CompanysRepositoty companysRepositoty;

//	@Autowired
//	EmployeesRepository employees;

	@Override
	public void run(String... params) throws Exception {

		// insert
		Companys company = new Companys();
		company.setName("Google");

		Employees emp1 = new Employees();
		emp1.setName("Gmail");
		emp1.setCompanys(company);

		Employees emp2 = new Employees();
		emp2.setName("Google photo");
		emp2.setCompanys(company);

		company.getListEmployees().add(emp1);
		company.getListEmployees().add(emp2);

		companysRepositoty.save(company);

		// delete
		Optional<Companys> entity = companysRepositoty.findById(2);
		entity.ifPresent(t -> companysRepositoty.delete(t));

	}

}

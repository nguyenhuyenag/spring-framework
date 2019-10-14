package core;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import core.entity.manytoone.onetomany.Company;
import core.entity.manytoone.onetomany.Staff;
import core.repository.CompanyRepositoty;

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
	CompanyRepositoty companyRepositoty;

	@Override
	public void run(String... params) throws Exception {
		// Student emp = new Student();
		// StudentId pk = new Student().new StudentId(1, 123);
		// System.out.println(studentRepository.findById(pk).get());
		Company company = companyRepositoty.findById(2).get();
		List<Staff> list = company.getListStaff();
		System.out.println(Arrays.toString(list.toArray()));
	}

}

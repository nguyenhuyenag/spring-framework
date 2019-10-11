package core;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import core.entity.manytoone.Company;
import core.entity.manytoone.Staff;
import core.repository.CompanyRepositoty;
import core.repository.StaffRepository;

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

	@Autowired
	StaffRepository staffRepository;

	@Override
	public void run(String... params) throws Exception {

		// Company
		Company company = new Company("LG");
		companyRepositoty.save(company);

		// Staff
		Staff staff1 = new Staff();
		staff1.setName(RandomStringUtils.randomAlphabetic(5));
		staff1.setCompany(company);
		staffRepository.save(staff1);

		// Staff
		Staff staff2 = new Staff();
		staff2.setName(RandomStringUtils.randomAlphabetic(5));
		staff2.setCompany(company);
		staffRepository.save(staff2);

	}

}

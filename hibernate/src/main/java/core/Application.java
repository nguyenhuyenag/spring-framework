package core;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import core.entity.Clazz;
import core.entity.manytoone.People;
import core.repository.ClazzRepository;
import core.repository.PeopleRepository;

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
	ClazzRepository clazzRepository;

	@Autowired
	PeopleRepository peopleRepository;

	@Override
	public void run(String... params) throws Exception {

		// clazz
		Clazz clazz = new Clazz(RandomStringUtils.randomAlphabetic(5));
		clazzRepository.save(clazz);

		// people
		People people1 = new People();
		people1.setName(RandomStringUtils.randomAlphabetic(5));
		people1.setClazz(clazz);
		peopleRepository.save(people1);

		// people
		People people2 = new People();
		people2.setName(RandomStringUtils.randomAlphabetic(5));
		people2.setClazz(clazz);
		peopleRepository.save(people2);

		// people
		People people3 = new People();
		people3.setName(RandomStringUtils.randomAlphabetic(5));
		people3.setClazz(clazz);
		peopleRepository.save(people3);

	}

}

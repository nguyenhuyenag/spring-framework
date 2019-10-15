package core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import core.entity.onetoone.Person;
import core.entity.onetoone.Students;
import core.repository.PersonRepository;
import core.repository.StudentsRepository;

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
	PersonRepository personRepository;

	@Autowired
	StudentsRepository studentsRepository;

	@Override
	public void run(String... params) throws Exception {

		Person person = new Person(null, "kai", "ha noi - viet nam");
		Students students = new Students(null, "CN10A", "PTIT", person);
		Students students2 = new Students(null, "CN10B", "FTU", person);

		studentsRepository.save(students);
		personRepository.save(person);

		studentsRepository.save(students2);

	}

}

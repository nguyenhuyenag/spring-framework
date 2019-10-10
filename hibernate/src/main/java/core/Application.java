package core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import core.repository.ClazzRepository;
import core.repository.StudentRepository;

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
	StudentRepository studentRepository;

	@Override
	public void run(String... params) throws Exception {

		// clazzRepository.findAll().forEach(System.out::println);
		// String name = RandomStringUtils.randomAlphabetic(6);
		// clazzRepository.save(new Clazz(name));
		// nameRepository.findAll().forEach(System.out::println);

		// Optional<Student> student = studentRepository.findById(new StudentId(1,
		// 123));
		// System.out.println(student.get().toString());
		System.out.println(studentRepository.getByJpaQuery(1, null));
		
	}

}

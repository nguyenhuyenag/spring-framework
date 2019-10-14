package core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import core.entity.embeddable.Student;
import core.entity.embeddable.Student.StudentId;
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
	StudentRepository studentRepository;

	@Override
	public void run(String... params) throws Exception {
		// Student emp = new Student();
		StudentId pk = new Student().new StudentId(1, 123);
		System.out.println(studentRepository.findById(pk).get());
	}

}

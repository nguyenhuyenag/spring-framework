package core;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import core.entity.enumerated.PositionEnum;
import core.entity.enumerated.Programing;
import core.repository.ProgramingRepositoty;

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
	ProgramingRepositoty repositoty;

	@Override
	public void run(String... args) throws Exception {

		Programing emp = new Programing();
		emp.setName("Java");
		emp.setPosition(PositionEnum.DEVELOPER);
		repositoty.save(emp);

		Optional<Programing> p = repositoty.findById(1);
		p.ifPresent(t -> System.out.println(t));
	}

}

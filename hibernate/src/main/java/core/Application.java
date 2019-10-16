package core;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import core.entity.manytoone.ecollection.Empl;
import core.repository.EmplRepository;

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
	EmplRepository empl;

	// @Autowired
	// EmplPositionRepository emplPosition;

	@Override
	public void run(String... params) throws Exception {

		// Empl entity = new Empl();
		// entity.setName("Java");
		//
		// List<String> positions = new ArrayList<>();
		// positions.add("Developer");
		// positions.add("Tester");
		// entity.setPositions(positions);
		//
		// empl.save(entity);

		Optional<Empl> emp = empl.findById(4);
		emp.ifPresent(t -> System.out.println(t.getListPositions()));
	}

}

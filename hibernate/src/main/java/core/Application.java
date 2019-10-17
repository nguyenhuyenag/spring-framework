package core;

import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import core.entity.Clazz;
import core.repository.ClazzRepository;

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
	ClazzRepository repositoty;

	@Override
	public void run(String... args) throws Exception {

		String name = RandomStringUtils.randomAlphabetic(5);
		Clazz entity = new Clazz(name);

		// insert
		repositoty.save(entity);

		Optional<Clazz> find = repositoty.findById(entity.getId());

		find.ifPresent(t -> {
			t.setName(RandomStringUtils.randomAlphabetic(5));
			repositoty.save(t);
		});
		
		repositoty.deleteById(find.get().getId());
	}

}

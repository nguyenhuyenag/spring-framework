package core;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger LOG = LoggerFactory.getLogger(Application.class);

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

		LOG.info("OK!");

		Clazz clazz = new Clazz(RandomStringUtils.randomAlphabetic(9));
		clazz.setAge(10000);
		repositoty.save(clazz);

		// repositoty.save(new Clazz());

		// Optional<Clazz> entity = repositoty.findById(9);
		// entity.ifPresent(t -> {
		// t.setName("C++");
		// System.out.println(t);
		// repositoty.save(t);
		// });

		repositoty.findAll().forEach(t -> System.out.println(t));
	}

}

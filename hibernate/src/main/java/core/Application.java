package core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import core.entity.manytomany.Category;
import core.entity.manytomany.Product;
import core.repository.CategoryRepository;

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
	CategoryRepository categoryRepository;

	@Override
	public void run(String... params) throws Exception {

		Category category = new Category( //
				"Electronic Device", //
				new Product("Television"), //
				new Product("Iphone"), //
				new Product("Samsung Galaxy S9") //
		);
		categoryRepository.save(category);

	}

}

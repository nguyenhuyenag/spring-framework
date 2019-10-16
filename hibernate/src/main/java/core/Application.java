package core;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import core.entity.manytomany.Product;
import core.repository.CategoryRepository;
import core.repository.ProductRepository;

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

	@Autowired
	ProductRepository productRepository;

	@Override
	public void run(String... params) throws Exception {

		// Product p1 = new Product("Television");
		// Product p2 = new Product("Iphone");
		// Product p3 = new Product("Samsung Galaxy S9");
		// Set<Product> set = new HashSet<>();
		// set.add(p1);
		// set.add(p2);
		// set.add(p3);
		//
		// Category category = new Category("Electronic Device");
		// category.setProducts(set);
		//
		// categoryRepository.save(category);

		// Category category2 = new Category("Home Applicances");

		Optional<Product> product = productRepository.findById(2);
		product.ifPresent(System.out::println);
		// product.ifPresent(p -> {
		// p.getCategorys().add(category2);
		// productRepository.save(p);
		// });

	}

}

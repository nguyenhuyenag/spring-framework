package learn;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import learn.of.component.servicerepository.Firefox;
import learn.of.component.servicerepository.FirefoxService;

@SpringBootApplication
public class LearnApplication implements CommandLineRunner {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(LearnApplication.class, args);

		// Lấy ra bean GirlService
		FirefoxService ieService = context.getBean(FirefoxService.class);

		// Lấu ra random một cô gái từ tầng service
		Firefox ie = ieService.getFirefoxByName();

		// In ra màn hình
		System.out.println(ie);
	}

	@Override
	public void run(String... args) throws Exception {

	}

}

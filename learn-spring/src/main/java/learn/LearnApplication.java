package learn;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import learn.of.component.autowired.Laptop;

@SpringBootApplication
public class LearnApplication implements CommandLineRunner {

	public static void main(String[] args) {

		// ApplicationContext chính là container, chứa toàn bộ các Bean
		ApplicationContext context = SpringApplication.run(LearnApplication.class, args);

		// Girl girl = context.getBean(Girl.class);
		
		Laptop laptop = context.getBean(Laptop.class);

		System.out.println("Girl Instance: " + laptop);

		System.out.println("Girl Outfit: " + laptop.getComputer());

		laptop.getComputer().info();
	}

	@Override
	public void run(String... args) throws Exception {

	}

}

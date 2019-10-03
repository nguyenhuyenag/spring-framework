package learn;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import learn.of.component.autowired.Primarys;
import learn.of.component.autowired.Qualifiers;

@SpringBootApplication
public class LearnApplication implements CommandLineRunner {

	public static void main(String[] args) {

		// ApplicationContext chính là container, chứa toàn bộ các Bean
		ApplicationContext context = SpringApplication.run(LearnApplication.class, args);

		Primarys primary = context.getBean(Primarys.class);
		primary.getInstance().getName();

		Qualifiers qualifier = context.getBean(Qualifiers.class);
		qualifier.getInstance().getName();

	}

	@Override
	public void run(String... args) throws Exception {

	}

}

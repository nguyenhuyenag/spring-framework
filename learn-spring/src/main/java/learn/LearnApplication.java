package learn;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import learn.of.component.autowired.primary.Primarys;

@SpringBootApplication
public class LearnApplication implements CommandLineRunner {

	public static void main(String[] args) {

		// ApplicationContext chính là container, chứa toàn bộ các Bean
		ApplicationContext context = SpringApplication.run(LearnApplication.class, args);

		// Qualifiers laptop = context.getBean(Qualifiers.class);
		Primarys laptop = context.getBean(Primarys.class);

		laptop.getOutfit().wear();
	}

	@Override
	public void run(String... args) throws Exception {

	}

}

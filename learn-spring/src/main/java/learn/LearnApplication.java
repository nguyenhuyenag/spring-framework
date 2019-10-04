package learn;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import learn.of.configuration.SimpleBean;

@SpringBootApplication
public class LearnApplication implements CommandLineRunner {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(LearnApplication.class, args);

		// Lấy ra bean SimpleBean trong Context
		SimpleBean simpleBean = context.getBean(SimpleBean.class);

		// In ra màn hình
		System.out.println("Simple Bean Example: " + simpleBean.toString());

	}

	@Override
	public void run(String... args) throws Exception {

	}

}

package learn;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import learn.of.component.life.cycle.BeanLifeCycle;
import learn.util.ConsoleColors;

@SpringBootApplication
public class LearnApplication implements CommandLineRunner {

	public static void main(String[] args) {
		System.out.println(ConsoleColors.RED + "> Trước khi IoC Container được khởi tạo" + ConsoleColors.RESET);
		ApplicationContext context = SpringApplication.run(LearnApplication.class, args);
		System.out.println("> Sau khi IoC Container được khởi tạo");
		// Khi chạy xong, lúc này context sẽ chứa các Bean có đánh dấu @Component.
		BeanLifeCycle girl = context.getBean(BeanLifeCycle.class);
		System.out.println("> Trước khi IoC Container destroy");
		((ConfigurableApplicationContext) context).getBeanFactory().destroyBean(girl);
		System.out.println("> Sau khi IoC Container destroy");
	}

	@Override
	public void run(String... args) throws Exception {

	}

}

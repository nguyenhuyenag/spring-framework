package learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import learn.of.unit1.Girl;
import learn.of.unit1.Outfit;

// Spring tạo ra một Container chứa các Dependency
@SpringBootApplication
public class LearnApplication {

	public static void main(String[] args) {

		// SpringApplication.run(LearnApplication.class, args); // <- create Container

		// ApplicationContext là container, chứa toàn bộ các Bean
		ApplicationContext context = SpringApplication.run(LearnApplication.class, args);

		// Khi chạy xong, lúc này context sẽ chứa các Bean có đánh
		// dấu @Component

		// Lấy Bean ra bằng cách
		Outfit outfit = context.getBean(Outfit.class);

		// In ra để xem thử nó là gì
		System.out.println("Output Instance: " + outfit);

		// xài hàm wear()
		outfit.wear();

		Girl girl = context.getBean(Girl.class);

		System.out.println("Girl Instance: " + girl);
		System.out.println("Girl Outfit: " + girl.getOutfit());

		girl.getOutfit().wear();
	}

}

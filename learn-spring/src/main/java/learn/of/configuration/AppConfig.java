package learn.of.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

	@Bean
	SimpleBean simpleBeanConfigure() {
		// Khởi tạo một instance của SimpleBean và trả ra ngoài
		return new SimpleBean("loda");
	}
}

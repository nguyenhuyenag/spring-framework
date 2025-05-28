package dev.boot.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*-
 * - 
 * 		ApplicationContext context = SpringApplication.run(Application.class, args);
 * 		// mysql
 * 		Connector mysql = (Connector) context.getBean("mysql");
 * 		mysql.connect();
 * 		// postgresql
 * 		Connector postgresql = (Connector) context.getBean("postgresql");
 * 		postgresql.connect();
 */
@Configuration
public class AppConfig {

	/**
	 * - Spring sẽ gọi những method này để khởi tạo một bean với kiểu dữ liệu tương
	 * ứng khi cần thiết. Mặc định, tên của bean sẽ giống với tên của method, nếu
	 * bạn muốn đổi tên của bean thì có thể truyền một tham số vào @Bean("name)
	 * annotation để chỉ định tên của bean
	 * 
	 * - Lưu ý rằng tất cả các method được chú thích bằng @Bean phải nằm trong các
	 * class @Configuration
	 */
	@Bean("mysql")
	Connector mysqlConfigure() {
		Connector mysql = new MySQL();
		mysql.setUrl("jdbc:mysql://localhost:3306/data");
		return mysql;
	}

	@Bean
	// Khởi tạo một instance của SimpleBean và trả ra ngoài
	SimpleBean simpleBeanConfigure() {
		return new SimpleBean("database_name");
	}

	@Bean("postgresql")
	Connector postgresqlConfigure(SimpleBean bean) { // Tự động inject SimpleBean
		Connector postgre = new PostgreSQL();
		postgre.setUrl("postgresql://localhost:3000/" + bean.getUsername());
		return postgre;
	}

}

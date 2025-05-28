package dev.boot.component.scan;

import org.springframework.stereotype.Component;

/*-
 * - Spring Boot sẽ đi tìm các Bean trong package chứa class main hoặc các
 * package con cùng cấp với class main.
 * 
 * - Nếu muốn chỉ định tìm kiếm trong một package nhất định thì có 2 cách sau:
 * 
 * 	1) Thêm @ComponentScan("com.boot.component.scan.sub") ở class main
 * 
 * 		ApplicationContext context = SpringApplication.run(Application.class, args);
 * 		try {
 * 			Edge edge = context.getBean(Edge.class);
 * 			edge.show();
 * 		} catch (Exception e) {
 * 			System.out.println("Bean Edge không tồn tại!");
 * 		}
 * 		try {
 * 			Google google = context.getBean(Google.class);
 * 			google.show();
 * 		} catch (Exception e) {
 * 			System.out.println("Bean Google không tồn tại!");
 * 		}
 * 
 * 	2) Dùng @SpringBootApplication(scanBasePackages = "scanBasePackages")
 * 
 * - Multiple package scan
 * 
 * 		+ @ComponentScan({ "com.package1", "com.package2" })
 * 
 * 		+ @SpringBootApplication(scanBasePackages = { "com.package1", "com.package2" })
 * 
 */
@Component
public class Edge {

	public void show() {
		System.out.println("I'm Firefox");
	}

}

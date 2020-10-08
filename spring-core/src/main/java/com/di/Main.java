package com.di;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*-
 * Injection by Constructor (CI) vs Injection by Setter (SI)
 * 
 * - Inject từng phần: Chỉ có thể inject từng thuộc tính bằng SI
 * 
 * - Overriding: SI ghi đè lại CI, nếu dùng cả 2, IoC sẽ sử dụng SI
 * 
 * - Chuyển đổi: Dễ dàng thay đổi giá trị bằng SI mà ko new instance
 * 
 * - Nếu dùng SI thì class phải có default constructor, dùng CI thì phải có hàm
 *   khởi tạo với các tham số tương ứng với injection
 */
public class Main {

	public static void main(String[] args) {
		// FileSystemXmlApplicationContext("classpath:di/context.xml");
		try (AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("di/context.xml");) {
			HelloWorld h1 = (HelloWorld) ctx.getBean("helloWorld1");
			h1.print();
			HelloWorld h2 = (HelloWorld) ctx.getBean("helloWorld2");
			h2.print();
		}
	}

}

package com.component.auto;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*-
 * Các Annotation:
 * 
 * @Component: Đây là một component được tự động scan.
 * 
 * @Repository: Đây là một DAO component trong tầng persistence.
 * 
 * @Service: Đây là một Service component trong tầng business.
 * 
 * @Controller: Đây là một Controller component trong tầng presentation
 * 
 * - Mặc định bean được tạo từ auto scan component có tên là tên của class với chữ đầu tiên viết thường
 */
public class Main {

	public static void main(String[] args) {
		try (AbstractApplicationContext context = new ClassPathXmlApplicationContext("scan/auto-context.xml");) {
			UserService userService = (UserService) context.getBean("userService");
			userService.findUser(1);
		}
	}

}

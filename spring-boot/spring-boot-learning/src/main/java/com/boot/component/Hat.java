package com.boot.component;

import org.springframework.stereotype.Component;

/*-
 * - Annotation @Component sẽ đánh dấu class Hat là một bean.
 * 
 * - ApplicationContext là nơi chứa toàn bộ dependency trong project.
 * 
 * 		ApplicationContext context = SpringApplication.run(Application.class, args);
 * 		Hat hat = context.getBean(Hat.class);
 * 		hat.wear();
 * 
 * - Nếu không có @Component, spring sẽ thông báo lỗi
 * 
 * 		NoSuchBeanDefinitionException: No qualifying bean of type ... available
 */
@Component
public class Hat {

	public void wear() {
		System.out.println("Đang đội nón");
	}

}

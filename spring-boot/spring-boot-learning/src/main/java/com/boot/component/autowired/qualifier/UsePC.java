package com.boot.component.autowired.qualifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/*-
 * - Cách sử dụng @Qualifier khi có 2 bean cùng loại
 * 
 * 		ApplicationContext ctx = SpringApplication.run(Application.class, args);
 * 		UsePC pc = ctx.getBean(UsePC.class);
 * 		pc.use();
 * 
 */
@Component
public class UsePC {

	// (1)
	// @Autowired
	// @Qualifier("asus")
	private PC pc;

	// (2)
	// @Autowired
	// public UsePC(@Qualifier("asus") PC pc) {
	//		this.pc = pc;
	// }

	// (3)
	@Autowired
	public void setPc(@Qualifier("asus") PC pc) {
		this.pc = pc;
	}

	public void use() {
		pc.start();
		System.out.println("Use PC");
	}

}

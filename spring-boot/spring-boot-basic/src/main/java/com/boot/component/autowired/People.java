package com.boot.component.autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*-
 * Các cách sử dụng @Autowired
 * 
 * 		ApplicationContext ctx = SpringApplication.run(Application.class, args);
 *		People people = ctx.getBean(People.class);
 * 		System.out.println(people);
 * 		System.out.println(people.outfit);
 * 		people.outfit.wear();
 * 
 */
@Component
public class People {

	// (1)
	@Autowired
	public Outfit outfit;

	/**
	 * Từ Spring phiên bản 4.3 chúng ta không cần chú thích @Autowired trên
	 * constructor, Spring sẽ tự hiểu và tìm các dependency tương ứng để tiêm vào.
	 * Tuy nhiên nếu có hơn 1 constructor thì chúng ta cần chỉ định @Autowired trên
	 * constructor được dùng để khai báo các dependency cần sử dụng trong Class
	 */
	// (2)
	// @Autowired
	public People(Outfit outfit) {
		this.outfit = outfit;
	}

	// (3)
	// @Autowired
	public void setOutfit(Outfit outfit) {
		this.outfit = outfit;
	}

	// (4): Không nên dùng
	public People() {
		// this.outfit = new Shirt();
	}

}

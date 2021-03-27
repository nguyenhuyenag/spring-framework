package com.boot.component.autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*-
 * Ba cách sử dụng @Autowired
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

package com.boot.component.autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 3 cách sử dụng @Autowired
 */ 
@Component
public class GirlFriend {

	// (1)
	@Autowired
	public Outfit outfit;

	// (4): Không nên dùng
	public GirlFriend() {
		// this.outfit = new Dress();
	}

	// (2)
	// @Autowired
	public GirlFriend(Outfit outfit) {
		this.outfit = outfit;
	}

	// (3)
	// @Autowired
	public void setOutfit(Outfit outfit) {
		this.outfit = outfit;
	}

	public Outfit getOutfit() {
		return outfit;
	}

}

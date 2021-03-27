package com.boot.component.autowired.primary;

import org.springframework.stereotype.Component;

@Component
public class HP implements Laptop {

	@Override
	public void start() {
		System.out.println("I'm HP");
	}

}

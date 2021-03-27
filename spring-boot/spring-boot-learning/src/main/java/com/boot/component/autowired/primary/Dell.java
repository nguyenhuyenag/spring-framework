package com.boot.component.autowired.primary;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class Dell implements Laptop {

	@Override
	public void start() {
		System.out.println("Start Dell laptop");
	}

}

package com.boot.component.autowired.qualifier;

import org.springframework.stereotype.Component;

@Component("acer")
public class Acer implements PC {

	@Override
	public void start() {
		System.out.println("Start Acer PC");
	}

}

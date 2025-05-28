package dev.boot.component.autowired;

import org.springframework.stereotype.Component;

@Component
public class Shirt implements Outfit {

	@Override
	public void wear() {
		System.out.println("I'm shirt");
	}

}

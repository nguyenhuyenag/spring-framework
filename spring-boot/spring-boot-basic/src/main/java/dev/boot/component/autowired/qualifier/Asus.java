package dev.boot.component.autowired.qualifier;

import org.springframework.stereotype.Component;

@Component("asus")
public class Asus implements PC {

	@Override
	public void start() {
		System.out.println("Start Asus PC");
	}

}

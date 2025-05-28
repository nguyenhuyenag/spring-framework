package dev.boot.component.autowired.primary;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary // bean luôn được ưu tiên
public class Dell implements Laptop {

	@Override
	public void start() {
		System.out.println("I'm Dell");
	}

}

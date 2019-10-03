package learn.of.component.primary;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import learn.of.component.Outfit;

@Primary
@Component
public class Naked implements Outfit {
	
	@Override
	public void wear() {
		System.out.println("Đang không mặc gì");
	}
	
}

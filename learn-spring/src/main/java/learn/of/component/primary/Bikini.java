package learn.of.component.primary;

import org.springframework.stereotype.Component;

import learn.of.component.Outfit;

@Component
public class Bikini implements Outfit {

	@Override
	public void wear() {
		System.out.println("Mặc bikini");
	}

}

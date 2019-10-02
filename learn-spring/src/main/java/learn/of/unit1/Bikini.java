package learn.of.unit1;

import org.springframework.stereotype.Component;

@Component
// @Scope("prototype")
public class Bikini implements Outfit {

	@Override
	public void wear() {
		System.out.println("Mặc bikini");
	}

}
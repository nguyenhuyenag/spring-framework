package learn.of.component.autowired.primary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import learn.of.component.Outfit;
import lombok.Data;

@Component
class Bikini implements Outfit {
	@Override
	public void wear() {
		System.out.println("Mặc bikini");
	}
}

@Primary
@Component
class Naked implements Outfit {

	@Override
	public void wear() {
		System.out.println("Đang không mặc gì");
	}

}

@Data
@Component
public class Primarys {

	@Autowired
	Outfit outfit;

}

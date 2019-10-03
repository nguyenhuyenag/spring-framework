package learn.of.component.autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import learn.of.component.Computer.Laptop;
import lombok.Data;

@Primary
@Component
class Asus implements Laptop {
	@Override
	public void getName() {
		System.out.println("Laptop Asus");
	}
}

@Component
class Acer implements Laptop {
	@Override
	public void getName() {
		System.out.println("Laptop Asus");
	}

}

@Data
@Component
public class Primarys {

	@Autowired // inject Asus
	Laptop instance;

}

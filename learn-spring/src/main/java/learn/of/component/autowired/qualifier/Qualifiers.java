package learn.of.component.autowired.qualifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import learn.of.component.Computer;
import lombok.Data;

@Component("dell")
class Dell implements Computer {
	@Override
	public void getInfo() {
		System.out.println("Dell");
	}
}

@Component("hp")
class Hp implements Computer {
	@Override
	public void getInfo() {
		System.out.println("HP");
	}
}

@Data
@Component
public class Qualifiers {

	@Autowired
	@Qualifier("dell")
	Computer computer;

}

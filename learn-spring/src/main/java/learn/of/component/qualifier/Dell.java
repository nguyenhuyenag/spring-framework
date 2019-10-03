package learn.of.component.qualifier;

import org.springframework.stereotype.Component;

import learn.of.component.Computer;

@Component("dell")
public class Dell implements Computer {

	@Override
	public void info() {
		System.out.println("Dell");
	}

}

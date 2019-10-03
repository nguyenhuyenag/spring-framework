package learn.of.component.qualifier;

import learn.of.component.Computer;

public class Hp implements Computer {

	@Override
	public void info() {
		System.out.println("HP");
	}

}

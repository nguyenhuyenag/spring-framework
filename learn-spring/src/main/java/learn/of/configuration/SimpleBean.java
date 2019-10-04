package learn.of.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SimpleBean {

	private String username;

	@Override
	public String toString() {
		return "This is a simple bean, name: " + username;
	}

}

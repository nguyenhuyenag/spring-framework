package learn.of.component.autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import learn.of.component.Computer;
import lombok.Data;

@Data
@Component
public class Laptop {

	@Autowired
	@Qualifier("dell")
	Computer computer;

}

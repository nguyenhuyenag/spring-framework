package com.boot.component.autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.boot.component.Computer.PC;

import lombok.Data;

@Component("dell")
class Dell implements PC {
	
	@Override
	public void getName() {
		System.out.println("PC Dell");
	}
	
}

@Component("hp")
class Hp implements PC {
	
	@Override
	public void getName() {
		System.out.println("PC HP");
	}
	
}

@Data
@Component
public class Qualifiers {

	@Autowired
	@Qualifier("dell")
	PC instance;

}

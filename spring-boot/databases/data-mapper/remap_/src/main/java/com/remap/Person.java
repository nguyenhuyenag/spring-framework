package com.remap;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Person {

	private String name;
	private String city;
	private int age;

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}

}

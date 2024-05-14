package com.remap;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Employee {

	private String name;
	private String address;
	private int age;

	@Override
	public String toString() {
		return "Employee [name=" + name + ", age=" + age + "]";
	}

}

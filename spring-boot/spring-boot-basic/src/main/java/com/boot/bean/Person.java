package com.boot.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Serializable {

	private static final long serialVersionUID = 5956882926782425852L;

	private int age;
	private String name;

	@Override
	public String toString() {
		return "Person [age=" + age + ", name=" + name + "]";
	}

}

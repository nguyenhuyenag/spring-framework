package com.autowire;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person {

	private String name;
	private int age;

	@Autowired(required = false)
	private Address address;

	public Person(Address address) {
		this.address = address;
	}

	public void print() {
		System.out.println("Person: " + this.name + " Age: " + this.age + "\nAddress: "
				+ (this.address == null ? "null" : this.address.toString()));
	}

}

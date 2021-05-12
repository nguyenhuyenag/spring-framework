package com.di.collection;

import java.util.List;

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
	private List<Address> listAddress;
	private List<String> listEmail;

	public void print() {
		System.out.println("Person: " + this.name + " Age: " + this.age);
		System.out.println("Address: ");
		for (Address address : listAddress) {
			System.out.println(address);
		}
		System.out.println("Email:");
		for (String email : listEmail) {
			System.out.println(email);
		}
	}

}

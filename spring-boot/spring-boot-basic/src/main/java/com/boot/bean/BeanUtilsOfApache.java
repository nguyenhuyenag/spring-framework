package com.boot.bean;

import java.util.Objects;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.SerializationUtils;

public class BeanUtilsOfApache {

	public static void cloneBean() {
		Person person = new Person(25, "John");
		// Shallow clone
		Person shallow = person; // . (Person) BeanUtils.cloneBean(person);
		// Deep clone
		Person deep = SerializationUtils.clone(person);

		person.setAge(100);

		System.out.println("Shallow: " + shallow.getAge());
		System.out.println("Deep: " + deep.getAge());
	}

	public static void main(String[] args) {
		cloneBean();
	}

}

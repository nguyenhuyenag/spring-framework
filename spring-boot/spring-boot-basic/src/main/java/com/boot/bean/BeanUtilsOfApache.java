package com.boot.bean;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.SerializationUtils;

public class BeanUtilsOfApache {

	public static void cloneBean() {
		Person person = new Person(25, "John");
		// Shallow clone
		Person shallow = person; // (Person) BeanUtils.cloneBean(person);
		// Deep clone
		Person deep = SerializationUtils.clone(person);
		// change orignal value
		person.setAge(100);
		System.out.println("Shallow: " + shallow.getAge());
		System.out.println("Deep: " + deep.getAge());
	}

	// Tạo một Map chứa các thuộc tính và giá trị tương ứng của một đối tượng
	public static void objectToMap() {
		Person person = new Person(25, "John");
		try {
			Map<String, String> describe = BeanUtils.describe(person);
			describe.forEach((k, v) -> System.out.println(k + ": " + v));
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	// Copy Map<> value to Java Object
	public static void mapToObject() {
		Map<String, String> map = new HashMap<>();
		map.put("name", "secret");
		map.put("age", "52");
		Person bean = new Person();
		try {
			BeanUtils.populate(bean, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(bean.toString());
	}

	public static void main(String[] args) {
		// cloneBean();
		// objectToMap();
		mapToObject();
	}

}

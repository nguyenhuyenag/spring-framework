package com.boot.bean;

import static org.apache.commons.beanutils.BeanUtils.describe;
import static org.apache.commons.beanutils.BeanUtils.populate;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.SerializationUtils;

public class BeanUtilsOfApache {

	public static void testCopyProperties() throws IllegalAccessException, InvocationTargetException {
		Person p1 = new Person(25, "John", List.of("ADMIN", "MOD", "USER"));
		Person p2 = new Person();
		BeanUtils.copyProperties(p2, p1);
		System.out.println(p2);
	}

	public static void cloneBean() {
		Person person = new Person(25, "John", List.of("ADMIN"));
		// Shallow clone
		Person shallow = person; // (Person) BeanUtils.cloneBean(person);
		// Deep clone
		Person deep = SerializationUtils.clone(person);
		// change orignal value
		person.setAge(100);
		System.out.println("Shallow: " + shallow.getAge());
		System.out.println("Deep: " + deep.getAge());
	}

	// Tạo Map chứa thuộc tính và giá trị tương ứng của đối tượng
	public static void objectToMap() {
		Person person = new Person(25, "John", List.of("ADMIN"));
		try {
			Map<String, String> describe = describe(person);
			System.out.println(describe);
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
			populate(bean, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(bean.toString());
	}

//	public static void main(String[] args) {
//		// cloneBean();
//		// objectToMap();
//		// mapToObject();
//	}

}

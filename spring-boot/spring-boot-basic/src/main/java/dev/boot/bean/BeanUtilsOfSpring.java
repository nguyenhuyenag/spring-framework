package dev.boot.bean;

import java.beans.PropertyDescriptor;
import java.util.List;

import org.springframework.beans.BeanUtils;

// @Service
// BeanUtils methods
public class BeanUtilsOfSpring {

	/*-
	 * - copyProperties(source, target, TargetObject.class); 
	 * 	 	-> Chỉ sao chép thuộc tính chỉ có trong class TargetObject
	 * 
	 * - copyProperties(source, target, ignoreProperties[]);
	 * 	 	-> Chỉ định các thuộc tính không cần copy 
	 */
	public static void testCopyProperties() {
		Person p1 = Person.builder() //
				.name("Jame") //
				.age(25) //
				.roles(List.of("ADMIN", "MOD", "USER")) //
				.build();

		// Copy all
		Person p2 = new Person();
		BeanUtils.copyProperties(p1, p2);

		// Ignore properties
		Person p3 = new Person();
		BeanUtils.copyProperties(p1, p3, "name", "roles");

		p1.setName("Jack");

		System.out.println("Copy all: " + p2);
		System.out.println("Ignore properties: " + p3);
	}

	public static void getPropertyDescriptors() {
		// Lấy danh sách các PropertyDescriptor cho đối tượng
		PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(BeanUtilsOfSpring.class);
		// Duyệt qua danh sách các PropertyDescriptor
		for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
			String propertyName = propertyDescriptor.getName();
			System.out.println("Property: " + propertyName);
			// Kiểm tra xem thuộc tính có getter không
			if (propertyDescriptor.getReadMethod() != null) {
				// Thực hiện các hoạt động liên quan đến getter
			}
			// Kiểm tra xem thuộc tính có setter không
			if (propertyDescriptor.getWriteMethod() != null) {
				// Thực hiện các hoạt động liên quan đến setter
			}
		}
	}

}

package com.remap;

import com.remondis.remap.Mapper;
import com.remondis.remap.Mapping;

/**
 * https://reflectoring.io/autotmatic-refactoring-safe-java-mapping/
 */
public class ReMapTest {

	protected static void sample() {
		Mapper<Person, Employee> mapper = Mapping //
				.from(Person.class) //
				.to(Employee.class) //
				// Omitting fields
				.omitInSource(Person::getCity) 				// Bỏ qua 'city' ở Source
				.omitInDestination(Employee::getAddress) 	// Bỏ qua 'address' ở Destination
				.mapper();

		Person person = new Person();
		person.setName("John Doe");
		person.setCity("London");
		person.setAge(30);

		Employee employee = mapper.map(person);

		System.out.println(employee);
	}

	public static void main(String[] args) {
		sample();
	}

}

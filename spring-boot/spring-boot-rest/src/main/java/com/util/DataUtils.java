package com.util;

import com.model.Employee;

public class DataUtils {

	public static Employee employee() {
		Employee employee = new Employee();
		employee.setId(10);
		employee.setContactNumber("999-999-9999");
		employee.setName("Test Employee");
		return employee;
	}

}

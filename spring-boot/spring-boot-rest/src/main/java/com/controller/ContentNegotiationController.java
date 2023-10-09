package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.Employee;

/*-
 * - Yêu cầu cấu hình trong WebConfig.configureContentNegotiation
 * 
 * 		http://localhost:8080/employee/?mediaType=xml
 * 
 * 		http://localhost:8080/employee/?mediaType=json
 */
@Controller
public class ContentNegotiationController {

	@ResponseBody
	@GetMapping(value = "employee")
	public Employee getEmployeeById() {
		Employee employee = new Employee();
		employee.setId(10);
		employee.setContactNumber("999-999-9999");
		employee.setName("Test Employee");
		return employee;
	}

}

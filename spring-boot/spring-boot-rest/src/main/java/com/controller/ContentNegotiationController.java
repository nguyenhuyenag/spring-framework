package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.Employee;
import com.util.DataUtils;

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
	public Employee getEmployee() {
		return DataUtils.employee();
	}

}

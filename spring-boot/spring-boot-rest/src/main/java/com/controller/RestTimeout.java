package com.controller;

import java.util.concurrent.Callable;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Employee;
import com.util.DataUtils;
import com.util.TimeWatch;

@RestController
public class RestTimeout {

	@GetMapping("request-timeout")
	public Callable<Employee> requestTimeout() {
		return () -> {
			TimeWatch.wasteTime();
	        return DataUtils.employee();
	    };
	}

}

package com.attyuttam.dynamicdatasourcerouting.infra.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.attyuttam.dynamicdatasourcerouting.domain.Employee;
import com.attyuttam.dynamicdatasourcerouting.domain.EmployeeService;
import com.attyuttam.dynamicdatasourcerouting.infra.datasource.DataSourceEnum;
import com.attyuttam.dynamicdatasourcerouting.infra.datasource.config.DataSourceContextHolder;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DetailsController {
	
	@Autowired
	ConfigurableApplicationContext context;
	
	private final EmployeeService employeeService;
	
	private final DataSourceContextHolder dataSourceContextHolder;

	@GetMapping(value = "/getEmployeeDetails/{dataSourceType}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Employee> getAllEmployeeDetails(@PathVariable("dataSourceType") String dataSourceType) {
		if (DataSourceEnum.DATASOURCE_TWO.toString().equals(dataSourceType)) {
			dataSourceContextHolder.setBranchContext(DataSourceEnum.DATASOURCE_TWO);
		} else {
			dataSourceContextHolder.setBranchContext(DataSourceEnum.DATASOURCE_ONE);
		}
		DefaultSingletonBeanRegistry registry = (DefaultSingletonBeanRegistry) context.getBeanFactory();
		String[] singletonNames = registry.getSingletonNames();
		for (String name : singletonNames) {
			if (!name.contains("spring")) {
				System.out.println(name);
			}
		}
		return employeeService.getAllEmployeeDetails();
	}
}

package com.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.model.Employee;

@Controller
@RequestMapping("form")
public class EmployeeController {

	@RequestMapping(value = "employee", method = RequestMethod.GET)
	public ModelAndView showForm() {
		return new ModelAndView("form/employee", "employee", new Employee());
	}

	@RequestMapping(value = "add-employee", method = RequestMethod.POST)
	public String submit(@Valid @ModelAttribute("employee") Employee employee, ModelMap model) {
		// BindingResult result
		// if (result.hasErrors()) {
		// 	return "error";
		// }
		model.addAttribute("name", employee.getName());
		model.addAttribute("contactNumber", employee.getContactNumber());
		model.addAttribute("id", employee.getId());
		return "form/add-employee";
	}
}

package com.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewController {
	
	@GetMapping("view")
	public String view() {
		return "view/view";
	}
	
	@GetMapping("view1")
	public ModelAndView view1() {
		return new ModelAndView("view/view1");
	}
	
}

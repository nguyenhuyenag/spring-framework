package com.cview;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ViewRestController {
	
	@GetMapping("view2")
	public String view2() {
		return "202012121212121212121";
	}
	
	@GetMapping("view3")
	public ModelAndView request3() {
		return new ModelAndView("view/view3");
	}
	
}

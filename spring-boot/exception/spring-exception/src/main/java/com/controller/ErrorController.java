package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// https://stackoverflow.com/questions/38777723/how-i-create-an-error-handler-404-500-in-spring-boot-mvc
@Controller
public class ErrorController {
	
	@GetMapping("403")
    public String forbidden(Model model) {
        return "error/403";
    }

    @GetMapping("404")
    public String notFound(Model model) {
        return "error/404";
    }

    @GetMapping("500")
    public String internal(Model model) {
        return "error/500";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "error/access-denied";
    }
}

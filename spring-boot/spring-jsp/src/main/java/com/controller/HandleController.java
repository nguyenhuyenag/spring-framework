package com.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Controller
public class HandleController {

	@GetMapping("send-request")
	public String request(HttpServletRequest req, Model model) {
		// Map<String, String[]> map = req.getParameterMap();
		// map.forEach((k, v) -> System.out.println(k + ": " + Arrays.toString(v)));

		ServletUriComponentsBuilder.fromCurrentRequest();
		ServletUriComponentsBuilder.fromCurrentRequestUri();

		// pattern will be either "/search/{subpath}/other" or
		// "/find/other/{subpath}", depending on the url requested
		String bestMatchPattern = (String ) req.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
		String path = (String) req.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		
		AntPathMatcher apm = new AntPathMatcher();
	    String finalPath = apm.extractPathWithinPattern(bestMatchPattern, path);
	    
		System.out.println("Pattern matched 1: " + path);
		System.out.println("Pattern matched 2: " + bestMatchPattern);
		System.out.println("Pattern final: " + finalPath);
		model.addAttribute("controller_path", bestMatchPattern);
		return "send-request";
	}

}

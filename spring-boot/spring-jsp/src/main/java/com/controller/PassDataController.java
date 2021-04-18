package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("pass-data")
public class PassDataController {
	
	@GetMapping("send-by-request-1")
	public String request1() {
		return "pass-data/send-by-request-1";
	}
	
	@GetMapping("send-by-request-2")
	public String request2() {
		return "pass-data/send-by-request-2";
	}
	
	@GetMapping("send-by-session-1")
	public String session1() {
		return "pass-data/send-by-session-1";
	}
	
	@GetMapping("send-by-session-2")
	public String session2() {
		return "pass-data/send-by-session-2";
	}
	
	@GetMapping("send-by-localstorage-1")
	public String localstorage1() {
		return "pass-data/send-by-localstorage-1";
	}
	
	@GetMapping("send-by-localstorage-2")
	public String localstorage2() {
		return "pass-data/send-by-localstorage-2";
	}
	
}

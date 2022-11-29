package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

	@GetMapping({ "/", "invoice" })
	public String hoadon() {
		return "invoice";
	}

	@GetMapping("count-unread-message")
	public String unreadMessage() {
		return "unread_message";
	}

}

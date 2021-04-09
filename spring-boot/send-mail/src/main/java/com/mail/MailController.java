package com.mail;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {

	@GetMapping("send-mail")
	private String send() {
		return "Status: " + MailService.toOne("nguyenhuyen_ag@yahoo.com");
	}
	
	@GetMapping("send-mail-cc")
	private String sendCC() {
		String email = "nguyenhuyenag@gmail.com, nguyenhuyen_ag@yahoo.com";
		return "Status: " + MailService.toManyCC(email);
	}
	
	@GetMapping("send-mail-bcc")
	private String sendBCC() {
		String email = "nguyenhuyenag@gmail.com, nguyenhuyen_ag@yahoo.com";
		return "Status: " + MailService.toManyBCC(email);
	}

}

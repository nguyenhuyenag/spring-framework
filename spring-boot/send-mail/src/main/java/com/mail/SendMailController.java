package com.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMailController {

	@Autowired
	private MailService mailService;

	@GetMapping("send-one")
	private String send() {
		return "Status: " + mailService.sendOne("huyennv.ts24@gmail.com");
	}

	@GetMapping("send-mail-cc")
	private String sendCC() {
		String email = "bathudaide@gmail.com, huyennv.ts24@gmail.com";
		return "Status: " + mailService.sendManyCC(email);
	}

	@GetMapping("send-mail-bcc")
	private String sendBCC() {
		String email = "bathudaide@gmail.com, huyennv.ts24@gmail.com";
		return "Status: " + mailService.sendManyBCC(email);
	}

	@GetMapping("send-mail-attachment")
	private String sendMailWithAttachment() {
		String email = "huyennv.ts24@gmail.com";
		return "Status: " + mailService.sendMailWithAttachment(email);
	}

}

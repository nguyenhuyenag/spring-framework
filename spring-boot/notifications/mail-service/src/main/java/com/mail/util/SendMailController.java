//package com.mail;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.mail.MessagingException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class SendMailController {
//
//	@Autowired
//	private MailJavaService mailService;
//
//	@Autowired
//	private MailSpringService mailSpringService;
//
//	@GetMapping({ "/", "home" })
//	public String request(Model model) {
//		List<String> list = new ArrayList<>();
//		list.add("send-one");
//		list.add("send-mail-cc");
//		list.add("send-mail-bcc");
//		list.add("send-mail-attachment");
//		model.addAttribute("listUrls", list);
//		return "home";
//	}
//
//	// ************** JAVA MAIL  ************** //
//
//	@GetMapping("send-one")
//	private String send() {
//		return "Status: " + mailService.sendOne("huyennv.ts24@gmail.com");
//	}
//
//	@GetMapping("send-mail-cc")
//	private String sendCC() {
//		String email = "bathudaide@gmail.com, huyennv.ts24@gmail.com";
//		return "Status: " + mailService.sendManyCC(email);
//	}
//
//	@GetMapping("send-mail-bcc")
//	private String sendBCC() {
//		String email = "bathudaide@gmail.com, huyennv.ts24@gmail.com";
//		return "Status: " + mailService.sendManyBCC(email);
//	}
//
//	@GetMapping("send-mail-attachment")
//	private String sendMailWithAttachment() {
//		String email = "huyennv.ts24@gmail.com";
//		return "Status: " + mailService.sendMailWithAttachment(email);
//	}
//
//	// ************** SPRING MAIL  ************** //
//
//	@GetMapping("spring-send-html")
//	private String springSendHtml() throws MessagingException {
//		return "Status: " + mailSpringService.sendHtmlEmail();
//	}
//
//}

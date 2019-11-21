package com.mail;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

/*-
 * - https://myaccount.google.com/lesssecureapps?pli=1
 * 
 * - Allow less secure apps => ON
 */
public class SendMail {

	public static void main(String[] args) {
		try (AbstractApplicationContext context = new ClassPathXmlApplicationContext("mail/mail-context.xml");) {
			MailSender mailSender = (MailSender) context.getBean("mailSender2");
			System.out.println("Sending text...");
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("bathudaide@gmail.com");
			message.setTo("bathudaide@gmail.com");
			message.setSubject("Send mail 2");
			message.setText("Send gmail using Spring");
			mailSender.send(message);
			System.out.println("Sending text done!");
		}
	}

}

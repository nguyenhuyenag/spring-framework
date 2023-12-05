package com.mail;

import java.io.File;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailSpringService {

	@Autowired
	private JavaMailSender javaMailSender;

//	public void sendEmail(String to, String subject, String body) {
//		SimpleMailMessage message = new SimpleMailMessage();
//		message.setTo(to);
//		message.setTo(new String[] {"recipient1@example.com", "recipient2@example.com"});
//		message.setSubject(subject);
//		message.setText(body);
//		mailSender.send(message);
//	}

	public void sendEmailWithAttachment(String to, String subject, String body) throws MessagingException, IOException {

		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(body);

		FileSystemResource file = new FileSystemResource(new File("attachment.jpg"));
		helper.addAttachment("attachment.jpg", file);

		javaMailSender.send(message);
	}

	public boolean sendHtmlEmail() throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();

		message.setFrom(new InternetAddress(PropertiesReader.MAIL_USERNAME));
		message.setRecipients(MimeMessage.RecipientType.TO, "bathudaide@gmail.com");
		message.setSubject("Test email from Spring");

		String htmlContent = "<h1>This is a test Spring Boot email</h1>" + //
				"<p>It can contain <strong>HTML</strong> content.</p>";

		message.setContent(htmlContent, "text/html; charset=utf-8");

		javaMailSender.send(message);
		return true;
	}

}

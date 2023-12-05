package com.mail;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailSpringService {

	@Autowired
	private JavaMailSender javaMailSender;

	/**
	 * Sending Simple Emails
	 */
	public void sendEmail(String to, String subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("noreply@baeldung.com");
		message.setTo(to);
		// message.setTo(new String[] {"recipient1@example.com"});
		message.setSubject(subject);
		message.setText(body);
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

		try {
			javaMailSender.send(message);
		} catch (MailException ex) {
			System.err.println(ex.getMessage());
		}
		return true;
	}

	/**
	 * Sending Emails With Attachments
	 */
	public void sendEmailWithAttachment(String to, String subject, String body) throws Exception {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(body);

		FileSystemResource file = new FileSystemResource(new File("attachment.jpg"));
		helper.addAttachment("attachment.jpg", file); // <tên file sẽ hiển thị trong mail, file> 

		javaMailSender.send(message);
	}

}

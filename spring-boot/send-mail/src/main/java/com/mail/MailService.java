package com.mail;

import java.io.File;
import java.io.IOException;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*-
 * - CC (Carbon Copy): 		 Người nhận xem được danh sách các người nhận
 * 
 * - BCC(Blind Carbon Copy): Người nhận không xem được danh sách các người nhận
 */
@Component
public class MailService {

	@Autowired
	private javax.mail.Session javaxSession;

	private final static String HOME = System.getProperty("user.dir");

//	private static final javax.mail.Session buildSession() {
//		Properties prop = new Properties();
//		prop.put("mail.smtp.host", "smtp.gmail.com");
//		prop.put("mail.smtp.port", "465");
//		prop.put("mail.smtp.auth", "true");
//		prop.put("mail.smtp.socketFactory.port", "465");
//		prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//		return javax.mail.Session.getInstance(prop, new javax.mail.Authenticator() {
//			protected PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication(PropertiesReader.MAIL_USERNAME, PropertiesReader.MAIL_PASSWORD);
//			}
//		});
//	}

	public boolean sendOne(String recipient) {
		try {
			Message message = new MimeMessage(javaxSession);
			message.setFrom(new InternetAddress(PropertiesReader.MAIL_USERNAME));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			message.setSubject("Testing Gmail");
			message.setText("Dear Mail, This is content of email.");
			Transport.send(message);
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * @param listRecipient là chuỗi các email phân cách bởi dấu phẩy
	 */
	private boolean sendMany(String listRecipient, RecipientType type) {
		try {
			Message message = new MimeMessage(javaxSession);
			message.setFrom(new InternetAddress(PropertiesReader.MAIL_USERNAME));
			message.setRecipients(type, InternetAddress.parse(listRecipient));
			message.setSubject("Testing Gmail");
			message.setText("Dear fen, this is content of email.");
			Transport.send(message);
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean sendManyCC(String recipient) {
		return sendMany(recipient, RecipientType.CC);
	}

	public boolean sendManyBCC(String recipient) {
		return sendMany(recipient, RecipientType.BCC);
	}

	public boolean sendMailWithAttachment(String recipient) {
		try {
			Message message = new MimeMessage(javaxSession);
			message.setFrom(new InternetAddress(PropertiesReader.MAIL_USERNAME));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			message.setSubject("Test Mail Subject");

			Multipart multipart = new MimeMultipart();

			// Content
			BodyPart content = new MimeBodyPart();
			content.setText("This is message body");
			multipart.addBodyPart(content);

			// AttachFile
			String[] fileNames = { "img1.jpg", "data.txt" };
			for (String name : fileNames) {
				MimeBodyPart attachmentPart = new MimeBodyPart();
				attachmentPart.attachFile(new File(HOME + "/file/" + name));
				multipart.addBodyPart(attachmentPart);
			}

			message.setContent(multipart);
			Transport.send(message);
			return true;
		} catch (MessagingException | IOException e) {
			e.printStackTrace();
		}
		return false;
	}

}

package com.mail;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/*-
 * 
 * - https://stackoverflow.com/a/59760500
 * 
 * - https://myaccount.google.com/lesssecureapps?pli=1
 * 
 * 		+ Allow less secure apps => ON
 * 
 * - CC (Carbon Copy): 		 Người nhận xem được danh sách các người nhận
 * 
 * - BCC(Blind Carbon Copy): Người nhận không xem được danh sách các người nhận
 */
public class MailService {

	private final static String HOME = System.getProperty("user.dir");

	private static final javax.mail.Session buildSession() {
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "465");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.socketFactory.port", "465");
		prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		return Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(PropertiesReader.MAIL_USERNAME, PropertiesReader.MAIL_PASSWORD);
			}
		});
	}

	public static boolean toOne(String email) {
		try {
			Message message = new MimeMessage(buildSession());
			message.setFrom(new InternetAddress(PropertiesReader.MAIL_USERNAME));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
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
	 * @param listEmail là chuỗi các email phân cách bởi dấu phẩy
	 */
	private static boolean toMany(String listEmail, RecipientType type) {
		try {
			Message message = new MimeMessage(buildSession());
			message.setFrom(new InternetAddress(PropertiesReader.MAIL_USERNAME));
			message.setRecipients(type, InternetAddress.parse(listEmail));
			message.setSubject("Testing Gmail");
			message.setText("Dear fen, this is content of email.");
			Transport.send(message);
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean toManyCC(String email) {
		return toMany(email, RecipientType.CC);
	}

	public static boolean toManyBCC(String email) {
		return toMany(email, RecipientType.BCC);
	}

	public static boolean sendMailWithAttachment(String email) {
		try {
			Message message = new MimeMessage(buildSession());
			message.setFrom(new InternetAddress(PropertiesReader.MAIL_USERNAME));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
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

			// Final
			message.setContent(multipart);
			Transport.send(message);
			return true;
		} catch (MessagingException | IOException e) {
			e.printStackTrace();
		}
		return false;
	}

}

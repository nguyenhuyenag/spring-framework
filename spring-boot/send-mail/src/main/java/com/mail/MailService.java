package com.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/*-
 * - https://myaccount.google.com/lesssecureapps?pli=1
 * 
 * - Allow less secure apps => ON
 * 
 * - CC (Carbon Copy): Người nhận xem được danh sách các người nhận
 * 
 * - (Blind Carbon Copy): Người nhận không xem được danh sách các người nhận
 */
public class MailService {

	private static final Session buildSession() {
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
	 * @param email là chuỗi các email phân cách bởi dấu phẩy
	 */
	public static boolean toManyCC(String email) {
		try {
			Message message = new MimeMessage(buildSession());
			message.setFrom(new InternetAddress(PropertiesReader.MAIL_USERNAME));
			message.setRecipients(RecipientType.CC, InternetAddress.parse(email));
			message.setSubject("Testing Gmail");
			message.setText("Dear Mail, This is content of email.");
			Transport.send(message);
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean toManyBCC(String email) {
		try {
			Message message = new MimeMessage(buildSession());
			message.setFrom(new InternetAddress(PropertiesReader.MAIL_USERNAME));
			message.addRecipients(RecipientType.BCC, InternetAddress.parse(email));
			message.setSubject("Testing Gmail");
			message.setText("Dear Mail, This is content of email.");
			Transport.send(message);
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return false;
	}

}

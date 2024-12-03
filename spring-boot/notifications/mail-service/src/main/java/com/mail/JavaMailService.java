package com.mail;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/*
    - CC (Carbon Copy): 		 Người nhận xem được danh sách các người nhận.
    - BCC (Blind Carbon Copy): Người nhận không xem được danh sách các người nhận.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class JavaMailService {

    private final javax.mail.Session javaxSession;

    private static final String HOME = System.getProperty("user.dir");

    public boolean sendText(String recipient, String subject, String textContent) {
        Message message = new MimeMessage(javaxSession);
        try {
            // message.setFrom(new InternetAddress(MAIL_SENDER));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            subject = MimeUtility.encodeText(subject, "utf-8", "Q");
            message.setSubject(subject);
            message.setText(textContent);
            Transport.send(message);
            log.info("Email sent successfully to {}", recipient);
            return true;
        } catch (MessagingException | UnsupportedEncodingException e) {
            log.error("Failed to send email to {}. Error: {}", recipient, e.getMessage(), e);
        }
        return false;
    }

    public boolean sendHtml(String recipient, String subject, String htmlContent) {
        Message message = new MimeMessage(javaxSession);
        try {
            // message.setFrom(new InternetAddress(MAIL_SENDER));
            message.setRecipient(RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(subject);
            message.setContent(htmlContent, "text/html; charset=utf-8");
            Transport.send(message);
            log.info("Email sent successfully to '{}'.", recipient);
            return true;
        } catch (MessagingException e) {
            log.error("Failed to send email to {}. Error: {}", recipient, e.getMessage(), e);
        }
        return false;
    }

    /**
     * @param listRecipient là chuỗi các email phân cách bởi dấu phẩy
     */
    private boolean sendMany(String listRecipient, RecipientType type) {
        try {
            Message message = new MimeMessage(javaxSession);
            // message.setFrom(new InternetAddress(MAIL_SENDER));
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
            // message.setFrom(new InternetAddress(MAIL_SENDER));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Test Mail Subject");

            Multipart multipart = new MimeMultipart();

            // Content
            BodyPart content = new MimeBodyPart();
            content.setText("This is message body");
            multipart.addBodyPart(content);

            // AttachFile
            String[] fileNames = {"img1.jpg", "data.txt"};
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

}

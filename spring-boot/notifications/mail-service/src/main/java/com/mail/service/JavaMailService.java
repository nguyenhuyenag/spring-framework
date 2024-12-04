package com.mail.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;

/*
    - CC (Carbon Copy):         Người nhận thấy được danh sách các người nhận.

    - BCC (Blind Carbon Copy):  Người nhận KHÔNG thấy được danh sách các người nhận.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class JavaMailService {

    private final javax.mail.Session javaxSession;

    private static final String HOME = System.getProperty("user.dir");

    private void logSendEmailSuccessfully(String recipient) {
        log.info("Email sent successfully to: {}", recipient);
    }

    private void logSendEmailFailed(String error) {
        log.error("Failed to send email. Error: {}", error);
    }

    public boolean sendText(String recipient, String subject, String textContent) {
        Message message = new MimeMessage(javaxSession);
        try {
            // message.setFrom(new InternetAddress(MAIL_SENDER));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            // MimeUtility.encodeText(subject, "utf-8", "Q");
            message.setSubject(subject);
            message.setText(textContent);
            Transport.send(message);
            logSendEmailSuccessfully(recipient);
            return true;
        } catch (MessagingException e) {
            logSendEmailFailed(e.getMessage());
        }
        return false;
    }

    public boolean sendHtml(String recipient, String subject, String htmlContent) {
        Message message = new MimeMessage(javaxSession);
        try {
            message.setRecipient(RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(subject);
            message.setContent(htmlContent, "text/html; charset=utf-8");
            Transport.send(message);
            logSendEmailSuccessfully(recipient);
            return true;
        } catch (MessagingException e) {
            logSendEmailFailed(e.getMessage());
        }
        return false;
    }

    //===============================================================

//    public boolean sendToManyCC(String recipients, String subject, String emailBody) {
//        return sendToMany(Arrays.asList(recipients.split(",")), RecipientType.CC, subject, emailBody);
//    }
//
//    public boolean sendToManyBCC(String recipients, String subject, String emailBody) {
//        return sendToMany(Arrays.asList(recipients.split(",")), RecipientType.BCC, subject, emailBody);
//    }

    /**
     * @param type = RecipientType.CC or RecipientType.BCC
     */
    public boolean sendToMany(List<String> recipients, RecipientType type, String subject, String emailBody) {
        Message message = new MimeMessage(javaxSession);
        try {
            String listRecipients = String.join(",", new HashSet<>(recipients));
            message.setRecipients(type, InternetAddress.parse(listRecipients));
            message.setSubject(subject);
            message.setText(emailBody);
            Transport.send(message);
            logSendEmailSuccessfully(listRecipients);
            return true;
        } catch (MessagingException e) {
            logSendEmailFailed(e.getMessage());
        }
        return false;
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
            log.error("Error: {}", e.getMessage());
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

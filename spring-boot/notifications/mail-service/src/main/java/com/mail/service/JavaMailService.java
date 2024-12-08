package com.mail.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.List;

import static com.mail.util.LogUtils.logSendEmailFailed;
import static com.mail.util.LogUtils.logSendEmailSuccessfully;

/*
    - CC (Carbon Copy):         Người nhận thấy được danh sách các người nhận.

    - BCC (Blind Carbon Copy):  Người nhận KHÔNG thấy được danh sách các người nhận.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class JavaMailService {

    private final javax.mail.Session javaxSession;

    @Value("${spring.mail.properties.mail.smtp.from}")
    private String mailSender;

    public Message buildMessage(String recipient, String subject, String body) {
        Message message = new MimeMessage(javaxSession);
        try {
            message.setFrom(new InternetAddress(mailSender, "Company XYZ"));
            message.setRecipient(RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(subject);
            message.setText(body);
        } catch (MessagingException | UnsupportedEncodingException e) {
            log.error("Error build mail message: {}", e.getMessage());
        }
        return message;
    }

    public boolean sendText(String to, String subject, String body) {
        try {
            Transport.send(buildMessage(to, subject, body));
            logSendEmailSuccessfully(to);
            return true;
        } catch (MessagingException e) {
            logSendEmailFailed(e);
        }
        return false;
    }

    public boolean sendHtml(String recipient, String subject, String htmlContent) {
        try {
            Message message = new MimeMessage(javaxSession);
            message.setRecipient(RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(subject);
            message.setContent(htmlContent, "text/html; charset=utf-8");
            Transport.send(message);
            logSendEmailSuccessfully(recipient);
            return true;
        } catch (MessagingException e) {
            logSendEmailFailed(e);
        }
        return false;
    }

    /*
        type = RecipientType.CC or RecipientType.BCC
     */
    public boolean sendToMany(List<String> recipients, RecipientType type, String subject, String emailBody) {
        try {
            Message message = new MimeMessage(javaxSession);
            String listRecipients = String.join(",", new HashSet<>(recipients));
            message.setRecipients(type, InternetAddress.parse(listRecipients));
            message.setSubject(subject);
            message.setText(emailBody);
            Transport.send(message);
            logSendEmailSuccessfully(listRecipients);
            return true;
        } catch (MessagingException e) {
            logSendEmailFailed(e);
        }
        return false;
    }

    public boolean sendMailWithAttachment(String recipient) {
        try {
            Message message = new MimeMessage(javaxSession);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Test mail with attachment");
            message.setContent(buildContentAttachment());
            Transport.send(message);
            logSendEmailSuccessfully(recipient);
            return true;
        } catch (MessagingException | IOException e) {
            logSendEmailFailed(e);
        }
        return false;
    }

    private static Multipart buildContentAttachment()
            throws MessagingException, IOException {
        String HOME = System.getProperty("user.dir");

        Multipart multipart = new MimeMultipart();

        // Content
        BodyPart content = new MimeBodyPart();
        content.setText("This is message body");
        multipart.addBodyPart(content);

        // Attachment
        String[] fileNames = {"data.txt", "funny-meme.jpg"};
        for (String name : fileNames) {
            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.attachFile(new File(HOME + "/attachment/" + name));
            multipart.addBodyPart(attachmentPart);
        }

        return multipart;
    }

}

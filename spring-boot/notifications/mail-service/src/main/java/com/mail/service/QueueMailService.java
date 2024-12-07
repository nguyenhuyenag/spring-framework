package com.mail.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Slf4j
@Service
@RequiredArgsConstructor
public class QueueMailService {

    private final javax.mail.Session javaxSession;

    private static final BlockingQueue<Message> queue = new LinkedBlockingQueue<>();

    @Value("${spring.mail.properties.mail.smtp.from}")
    private String mailSender;

    @PostConstruct
    private void initialize() {
        start(); // Gọi hàm start() ngay khi bean khởi tạo
    }

    private Message buildMessage(String recipient, String subject, String body) {
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

    private void start() {
        Thread thread = new Thread(() -> {
            while (!queue.isEmpty()) {
                try {
                    Message message = queue.take();
                    Transport.send(message);
                    System.out.println("Sent mail: " + Arrays.toString(message.getReplyTo()));
                } catch (InterruptedException e) {
                    break;
                } catch (Throwable e) {
                    System.out.println("Send mail error: " + e);
                }
            }
        });
        thread.setName("Mail-sender");
        thread.start();
    }

    public void sendMail(String to, String subject, String text) {
        Message message = buildMessage(to, subject, text);
        queue.add(message);
    }

}

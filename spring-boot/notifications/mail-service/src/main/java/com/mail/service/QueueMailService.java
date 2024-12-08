package com.mail.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Slf4j
@Service
// @RequiredArgsConstructor
public class QueueMailService {

    private final JavaMailService javaMailService;
    private static final BlockingQueue<Message> queue = new LinkedBlockingQueue<>();

    // @Value("${spring.mail.properties.mail.smtp.from}")
    // private String mailSender;

//    private Message buildMessage(String recipient, String subject, String body) {
//        Message message = new MimeMessage(javaxSession);
//        try {
//            message.setFrom(new InternetAddress(mailSender, "Company XYZ"));
//            message.setRecipient(RecipientType.TO, new InternetAddress(recipient));
//            message.setSubject(subject);
//            message.setText(body);
//        } catch (MessagingException | UnsupportedEncodingException e) {
//            log.error("Error build mail message: {}", e.getMessage());
//        }
//        return message;
//    }

    public QueueMailService(JavaMailService javaMailService) {
        this.javaMailService = javaMailService;
        Thread workerThread = new Thread(() -> {
            while (true) {
                try {
                    Message message = queue.take();
                    Transport.send(message); // Gửi email
                    log.info("Email sent successfully");
                } catch (MessagingException | InterruptedException e) {
                    log.error("Failed to send email: {}", e.getMessage());
                }
            }
        });

        workerThread.setDaemon(true); // Đảm bảo thread sẽ dừng khi ứng dụng dừng
        workerThread.start();
    }

    public void sendMail(String to, String subject, String text) {
        Message message = javaMailService.buildMessage(to, subject, text);
        queue.add(message);
    }

}

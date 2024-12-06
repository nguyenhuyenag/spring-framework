package com.mail.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.mail.util.LogUtils.logSendEmailFailed;
import static com.mail.util.LogUtils.logSendEmailSuccessfully;

/*-
    - Để gửi một email thông qua SMTP có thể mất đến vài giây trong trường hợp xấu nhất, như vậy thì nó sẽ làm block luồng xử lý yêu cầu đang gọi gửi email như vậy làm khả năng phục vụ của toàn bộ hệ thống gặp vấn đề, sẽ có những yêu cầu đến sau phải chờ đợi rất lâu.

    - Để giải quyết vấn đề này chúng ta phải sử dụng kỹ thuật lập trình sử dụng blocking queue và đa luồng để tách phần gửi mail ra khỏi luồng xử lý, lúc này mã nguồn của EmailService sẽ như sau:
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AsyncMailService {

    private final javax.mail.Session javaxSession;
    private final ExecutorService executorService = Executors.newFixedThreadPool(2); // 2 luồng cho gửi email

    @Value("${spring.mail.properties.mail.smtp.from}")
    private String mailSender;

    private Message buildMessage(String recipient, String subject, String body)
            throws MessagingException, UnsupportedEncodingException {
        Message message = new MimeMessage(javaxSession);
        message.setFrom(new InternetAddress(mailSender, "Company XYZ"));
        message.setRecipient(RecipientType.TO, new InternetAddress(recipient));
        message.setSubject(subject);
        message.setText(body);
        return message;
    }

    private boolean sendText(String recipient, String subject, String body) {
        log.info("Start AsyncMailService.sendText() on thread: {}", Thread.currentThread().getName());
        try {
            Transport.send(buildMessage(recipient, subject, body));
            logSendEmailSuccessfully(recipient);
            return true;
        } catch (MessagingException | UnsupportedEncodingException e) {
            logSendEmailFailed(e);
        }
        return false;
    }

    public void sendByExecutorService(String recipient, String subject, String body) {
        executorService.submit(() -> sendText(recipient, subject, body));
    }

    public CompletableFuture<Boolean> sendByCompletableFuture(String recipient, String subject, String body) {
        return CompletableFuture.supplyAsync(() -> sendText(recipient, subject, body));
    }

    @Async // Important -> Bật @EnableAsync ở SpringMailApplication.java
    public CompletableFuture<Boolean> sendByAsync(String recipient, String subject, String textContent) {
        log.info("Start AsyncMailService.sendByAsync() on thread: {}", Thread.currentThread().getName());
        try {
            Transport.send(buildMessage(recipient, subject, textContent));
            logSendEmailSuccessfully(recipient);
            return CompletableFuture.completedFuture(true);
        } catch (MessagingException | UnsupportedEncodingException e) {
            logSendEmailFailed(e);
        }
        return CompletableFuture.completedFuture(false);
    }

    @PreDestroy
    public void shutdownService() {
        executorService.shutdown(); // Tắt ExecutorService
        System.out.println("ExecutorService has been shut down.");
    }

}

package com.mail.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import javax.mail.MessagingException;
import javax.mail.Transport;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.mail.util.LogUtils.logSendEmailFailed;
import static com.mail.util.LogUtils.logSendEmailSuccessfully;

/*-
    - Để gửi một email thông qua SMTP có thể mất đến vài giây trong trường hợp xấu nhất, như vậy thì nó sẽ làm block luồng xử lý yêu cầu đang gọi gửi email như vậy làm khả năng phục vụ của toàn bộ hệ thống gặp vấn đề, sẽ có những yêu cầu đến sau phải chờ đợi rất lâu.

    - Để giải quyết vấn đề này chúng ta phải sử dụng kỹ thuật lập trình sử dụng blocking queue và đa luồng để tách phần gửi mail ra khỏi luồng xử lý, lúc này mã nguồn của EmailService sẽ như sau.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AsyncMailService {

    private final JavaMailService javaMailService;
    private final ExecutorService executorService = Executors.newFixedThreadPool(2); // 2 luồng cho gửi email

    private boolean sendMail(String recipient, String subject, String body) {
        log.info("Start AsyncMailService.sendMail() on thread: {}", Thread.currentThread().getName());
        try {
            Transport.send(javaMailService.buildMessage(recipient, subject, body));
            logSendEmailSuccessfully(recipient);
            return true;
        } catch (MessagingException e) {
            logSendEmailFailed(e);
        }
        return false;
    }

    public void sendByExecutorService(String recipient, String subject, String body) {
        executorService.submit(() -> sendMail(recipient, subject, body));
    }

    public CompletableFuture<Boolean> sendByCompletableFuture(String recipient, String subject, String body) {
        return CompletableFuture.supplyAsync(() -> sendMail(recipient, subject, body));
    }

    @Async // Important -> Bật @EnableAsync ở SpringMailApplication.java
    public CompletableFuture<Boolean> sendByAsync(String recipient, String subject, String textContent) {
        log.info("Start AsyncMailService.sendByAsync() on thread: {}", Thread.currentThread().getName());
        try {
            Transport.send(javaMailService.buildMessage(recipient, subject, textContent));
            logSendEmailSuccessfully(recipient);
            return CompletableFuture.completedFuture(true);
        } catch (MessagingException e) {
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

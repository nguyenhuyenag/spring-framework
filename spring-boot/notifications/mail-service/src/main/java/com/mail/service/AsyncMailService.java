package com.mail.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.mail.util.LogUtils.logSendEmailFailed;
import static com.mail.util.LogUtils.logSendEmailSuccessfully;

/*-
    - Để gửi một email thông qua SMTP có thể mất đến vài giây trong trường hợp xấu nhất,
    như vậy thì nó sẽ làm block luồng xử lý yêu cầu đang gọi gửi email như vậy làm khả
    năng phục vụ của toàn bộ hệ thống gặp vấn đề, sẽ có những yêu cầu đến sau phải chờ
    đợi rất lâu.

    - Để giải quyết vấn đề này chúng ta phải sử dụng kỹ thuật lập trình sử dụng blocking
    queue và đa luồng để tách phần gửi mail ra khỏi luồng xử lý, lúc này mã nguồn của
    EmailService sẽ như sau.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AsyncMailService {

    private final JavaMailService javaMailService;
    private static final ExecutorService executorService = Executors.newFixedThreadPool(5);

    private void log(String method) {
        log.info("Start {} on thread: {}", method, Thread.currentThread().getName());
    }

    private void sendMail(String recipient, String subject, String body) {
        log("sendMail()");
        try {
            Message mail = javaMailService.buildMessage(recipient, subject, body);
            Transport.send(mail);
            logSendEmailSuccessfully(recipient);
        } catch (MessagingException e) {
            logSendEmailFailed(e);
        }
    }

    public void sendByExecutorService(String recipient, String subject, String body) {
        executorService.submit(() -> {
            try {
                sendMail(recipient, subject, body);
            } catch (Exception e) {
                log.error("Error while sending email: {}", e.getMessage(), e);
            }
        });
    }

    public CompletableFuture<Boolean> sendByCompletableFuture(String recipient, String subject, String body) {
        return CompletableFuture.supplyAsync(() -> {
            log("supplyAsync()");
            try {
                Transport.send(javaMailService.buildMessage(recipient, subject, body));
                logSendEmailSuccessfully(recipient);
                return true;
            } catch (MessagingException e) {
                logSendEmailFailed(e);
            }
            return false;
        });
    }

    /*
        - Cần bật @EnableAsync ở SpringMailApplication.java
        - Xem thêm cấu hình @Async trong spring-events
     */
    @Async("mailSenderTaskExecutor")
    public void sendByAsync(String recipient, String subject, String body) {
        log("sendByAsync()");
        try {
            Transport.send(javaMailService.buildMessage(recipient, subject, body));
            logSendEmailSuccessfully(recipient);
            // return CompletableFuture.completedFuture(true);
        } catch (MessagingException e) {
            logSendEmailFailed(e);
        }
    }

    @PreDestroy
    public void shutdownService() {
        executorService.shutdown(); // Tắt ExecutorService
    }

}

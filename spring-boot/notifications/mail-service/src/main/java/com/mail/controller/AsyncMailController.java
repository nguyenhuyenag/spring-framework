package com.mail.controller;

import com.mail.service.AsyncMailService;
import com.mail.service.QueueMailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

/*
    Cách sử dụng join() hoặc get() chỉ dùng khi thực sự cần kết quả ngay lập tức và
    không quan tâm đến việc block thread.
 */
@Slf4j
@RestController
@RequestMapping("async-mail")
@RequiredArgsConstructor
public class AsyncMailController {

    private final AsyncMailService asyncMailService;
    private final QueueMailService queueMailService;

    String body = "This is mail body.";

    private void log(String method) {
        log.info("Start {} on thread: {}", method, Thread.currentThread().getName());
    }

    // Không quan tâm kết quả gửi mail
    @PostMapping("/send-by-executor-service")
    public ResponseEntity<?> sendByExecutorService(String recipient) {
        log("sendByExecutorService()");

        String subject = "Async mail using Executor Service " + System.currentTimeMillis();

        asyncMailService.sendByExecutorService(recipient, subject, body);

        return ResponseEntity.noContent().build();
    }

    // Không quan tâm kết quả gửi mail
    @PostMapping("/send-by-completable-future")
    public ResponseEntity<?> sendByCompletableFuture(String recipient) {
        log("sendByCompletableFuture()");

        String subject = "Async mail using CompletableFuture " + System.currentTimeMillis();

        asyncMailService.sendByCompletableFuture(recipient, subject, body)
                .thenAccept(result -> {
                    if (result) {
                        log.info("Email sent successfully to: {}", recipient);
                    } else {
                        log.info("Failed to send email to: {}", recipient);
                    }
                });

        return ResponseEntity.noContent().build();
    }

    // Quan tâm kết quả gửi mail
    @PostMapping("/send-by-completable-future-join")
    public ResponseEntity<?> sendByCompletableFutureJoin(String recipient) {
        log("sendByCompletableFutureJoin()");
        String subject = "Async mail using CompletableFuture_Join" + System.currentTimeMillis();

        CompletableFuture<Boolean> result = asyncMailService.sendByCompletableFuture(recipient, subject, body);

        // Chặn luồng chính cho đến khi tác vụ bất đồng bộ hoàn thành và trả về kết quả -> chậm chương trình
        boolean success = result.join();

        // Trả về kết quả trong ResponseEntity
        if (success) {
            return ResponseEntity.ok("Email sent successfully!");
        } else {
            return ResponseEntity.status(500).body("Failed to send email.");
        }
    }

    @PostMapping("/send-by-async")
    public ResponseEntity<?> sendByAsync(String recipient) {
        log("sendByCompletableFutureJoin()");

        String subject = "Async mail using @Async " + System.currentTimeMillis();
        try {
            CompletableFuture<Boolean> result = asyncMailService.sendByAsync(recipient, subject, body);
            // Chờ kết quả của CompletableFuture, but block main thread
            boolean isSuccess = result.get();
            if (isSuccess) {
                return ResponseEntity.ok(true);
            }
        } catch (Exception e) {
            log.error("Error while sending email: {}", e.getMessage(), e);
        }
        return ResponseEntity.ok(false);
    }

    @PostMapping("/queue-mail")
    public ResponseEntity<?> queueMail(String recipient) {
        log("blockingQueue()");

        String subject = "Queue mail using BlockingQueue " + System.currentTimeMillis();

        queueMailService.sendMail(recipient, subject, body);

        return ResponseEntity.ok(null);
    }

}

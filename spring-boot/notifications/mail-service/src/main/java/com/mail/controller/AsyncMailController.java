package com.mail.controller;

import com.mail.service.AsyncMailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequestMapping("async-mail")
@RequiredArgsConstructor
public class AsyncMailController {

    private final AsyncMailService asyncMailService;

    private final String subject = "[Async] Chào bạn, đây là email thử nghiệm.";
    private final String textContent = "This is mail body.";

    private void log(String method, String threadName) {
        log.info("Start {} on thread: {}", method, threadName);
    }

    @PostMapping("/send-by-executor-service")
    public ResponseEntity<?> sendByExecutorService(String recipient) {
        log("AsyncMailController.sendByExecutorService()", Thread.currentThread().getName());
        asyncMailService.sendByExecutorService(recipient, subject, textContent);
        return ResponseEntity.ok(null);
    }

    // Không quan tâm kết quả gửi mail
    @PostMapping("/send-by-completable-future")
    public ResponseEntity<?> sendByCompletableFuture(String recipient) {
        log("AsyncMailController.sendByCompletableFuture()", Thread.currentThread().getName());
        CompletableFuture<Boolean> sendEmailFuture = asyncMailService.sendByCompletableFuture(recipient, subject, textContent);
        sendEmailFuture.thenAccept(success -> {
            if (success) {
                System.out.println("Email sent successfully to: " + recipient);
            } else {
                System.out.println("Failed to send email to: " + recipient);
            }
        });
        return ResponseEntity.ok(null);
    }

    // Quan tâm kết quả gửi mail
    @PostMapping("/send-by-completable-future-join")
    public ResponseEntity<?> sendByCompletableFutureJoin(String recipient) {
        log("AsyncMailController.sendByCompletableFuture()", Thread.currentThread().getName());
        CompletableFuture<Boolean> sendEmailFuture = asyncMailService.sendByCompletableFuture(recipient, subject, textContent);

        // Chặn luồng chính cho đến khi tác vụ bất đồng bộ hoàn thành và trả về kết quả -> chậm chương trình
        boolean success = sendEmailFuture.join();  // join() sẽ chặn và lấy kết quả

        // Trả về kết quả trong ResponseEntity
        if (success) {
            return ResponseEntity.ok("Email sent successfully!");
        } else {
            return ResponseEntity.status(500).body("Failed to send email.");
        }
    }

    @PostMapping("/send-by-async")
    public ResponseEntity<?> sendByAsync(String recipient) {
        try {
            CompletableFuture<Boolean> result = asyncMailService.sendByAsync(recipient, subject, textContent);
            // Chờ kết quả của CompletableFuture, but block main thread
            boolean isSuccess = result.get();
            if (isSuccess) {
                return ResponseEntity.ok(isSuccess);
            }
        } catch (Exception e) {
            log.error("Error while sending email: {}", e.getMessage(), e);
        }
        return ResponseEntity.ok(false);
    }

}

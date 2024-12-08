package com.mail.controller;

import com.mail.service.AsyncMailService;
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
    // private final QueueMailService queueMailService;

    private void log(String method) {
        log.info("Start {} on thread: {}", method, Thread.currentThread().getName());
    }

    @PostMapping("/send-by-executor-service")
    public ResponseEntity<?> sendByExecutorService(String recipient) {
        log("sendByExecutorService()");
        String subject = "[SendByExecutorService] Chào bạn, đây là email thử nghiệm.";
        String textContent = "This is mail body.";
        asyncMailService.sendByExecutorService(recipient, subject, textContent);
        return ResponseEntity.ok(null);
    }

//    // Không quan tâm kết quả gửi mail
//    @PostMapping("/send-by-completable-future")
//    public ResponseEntity<?> sendByCompletableFuture(String recipient) {
//        String subject = "[SendByCompletableFuture] Chào bạn, đây là email thử nghiệm.";
//        log("AsyncMailController.sendByCompletableFuture()", Thread.currentThread().getName());
//        CompletableFuture<Boolean> sendEmailFuture = asyncMailService.sendByCompletableFuture(recipient, subject, textContent);
//        sendEmailFuture.thenAccept(success -> {
//            if (success) {
//                System.out.println("Email sent successfully to: " + recipient);
//            } else {
//                System.out.println("Failed to send email to: " + recipient);
//            }
//        });
//        return ResponseEntity.ok(null);
//    }
//
//    // Quan tâm kết quả gửi mail
//    @PostMapping("/send-by-completable-future-join")
//    public ResponseEntity<?> sendByCompletableFutureJoin(String recipient) {
//        String subject = "[SendByCompletableFutureJoin] Chào bạn, đây là email thử nghiệm.";
//        log("AsyncMailController.sendByCompletableFuture()", Thread.currentThread().getName());
//        CompletableFuture<Boolean> sendEmailFuture = asyncMailService.sendByCompletableFuture(recipient, subject, textContent);
//
//        // Chặn luồng chính cho đến khi tác vụ bất đồng bộ hoàn thành và trả về kết quả -> chậm chương trình
//        boolean success = sendEmailFuture.join();
//
//        // Trả về kết quả trong ResponseEntity
//        if (success) {
//            return ResponseEntity.ok("Email sent successfully!");
//        } else {
//            return ResponseEntity.status(500).body("Failed to send email.");
//        }
//    }
//
//    @PostMapping("/send-by-async")
//    public ResponseEntity<?> sendByAsync(String recipient) {
//        String subject = "[SendByAsync] Chào bạn, đây là email thử nghiệm.";
//        try {
//            CompletableFuture<Boolean> result = asyncMailService.sendByAsync(recipient, subject, textContent);
//            // Chờ kết quả của CompletableFuture, but block main thread
//            boolean isSuccess = result.get();
//            if (isSuccess) {
//                return ResponseEntity.ok(true);
//            }
//        } catch (Exception e) {
//            log.error("Error while sending email: {}", e.getMessage(), e);
//        }
//        return ResponseEntity.ok(false);
//    }

//    @PostMapping("/blocking-queue")
//    public ResponseEntity<?> blockingQueue(String recipient) {
//        String subject = "[Queue] Chào bạn, đây là email thử nghiệm.";
//        String textContent = "This is mail body.";
//        queueMailService.sendMail(recipient, subject, textContent);
//        return ResponseEntity.ok(null);
//    }

}

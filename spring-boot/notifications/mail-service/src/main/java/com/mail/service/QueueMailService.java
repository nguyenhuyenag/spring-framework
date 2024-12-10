package com.mail.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import java.util.Arrays;
import java.util.concurrent.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class QueueMailService {

    private final JavaMailService javaMailService;

    // Giới hạn kích thước hàng đợi
    private static final BlockingQueue<Message> queue = new LinkedBlockingQueue<>(1000);

    // Pool với 5 thread
    private static final ExecutorService mailExecutor = Executors.newFixedThreadPool(5);

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.info("Shutting down MailWorker...");
            mailExecutor.shutdown();
            try {
                if (!mailExecutor.awaitTermination(5, TimeUnit.SECONDS)) {
                    log.warn("Forcing shutdown of MailWorker...");
                    mailExecutor.shutdownNow();
                }
            } catch (InterruptedException e) {
                log.error("Error while shutting down MailWorker: {}", e.getMessage());
                mailExecutor.shutdownNow();
            }
        }));

        for (int i = 0; i < 5; i++) {
            int worker = i + 1;
            mailExecutor.submit(() -> {
                log.info("Start MailWorker {}", worker);
                while (true) {
                    try {
                        Message message = queue.take(); // Chờ đến khi có email
                        Transport.send(message); // Gửi email
                        Address[] recipients = message.getRecipients(Message.RecipientType.TO);
                        log.info("Email sent successfully to: {}", Arrays.toString(recipients));
                    } catch (MessagingException e) {
                        log.error("Failed to send email: {}", e.getMessage());
                        // TODO: Thêm logic lưu hoặc retry email thất bại
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt(); // Đảm bảo thread thoát an toàn
                        break;
                    }
                }
            });
        }
    }

    public void sendMail(String to, String subject, String text) {
        try {
            Message message = javaMailService.buildMessage(to, subject, text);
            if (!queue.offer(message, 5, TimeUnit.SECONDS)) {
                log.warn("Failed to queue email: {}", to);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("Interrupted while queuing email to: {}", to);
        }
    }


}

package com.mail.controller;

import com.mail.service.JavaMailService;
import com.mail.service.SpringMailService;
import com.mail.util.FileManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
public class MailController {

    private final JavaMailService javaMailService;
    private final SpringMailService springMailService;

    @PostMapping("/send-text")
    public ResponseEntity<?> sendText(String recipient) {
        String subject = "text_mail_subject";
        String textContent = "This is a test mail";
        return ResponseEntity.ok(javaMailService.sendText(recipient, subject, textContent));
    }

    @PostMapping("/send-html")
    public ResponseEntity<?> sendHtml(String recipient) {
        String subject = "html_mail_subject";
        String textContent = FileManager.getFileFromResource("email_template.html");
        return ResponseEntity.ok(javaMailService.sendHtml(recipient, subject, textContent));
    }

}

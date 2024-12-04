package com.mail.controller;

import com.mail.service.JavaMailService;
import com.mail.service.SpringMailService;
import com.mail.util.FileManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.Message;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
public class MailController {

    private final JavaMailService javaMailService;
    private final SpringMailService springMailService;

    @PostMapping("/send-text")
    public ResponseEntity<?> sendText(String recipient) {
        String subject = "Chào bạn, đây là email thử nghiệm (text)!";
        String textContent = "This is a text mail";
        return ResponseEntity.ok(javaMailService.sendText(recipient, subject, textContent));
    }

    @PostMapping("/send-html")
    public ResponseEntity<?> sendHtml(String recipient) {
        String subject = "Chào bạn, đây là email thử nghiệm (html)!";
        String textContent = FileManager.getFileFromResource("email_template.html");
        return ResponseEntity.ok(javaMailService.sendHtml(recipient, subject, textContent));
    }

    @PostMapping("/send-to-many-cc")
    public ResponseEntity<?> sendToManyCC(String recipients) {
        String subject = "Chào bạn, đây là email thử nghiệm (text)!";
        String textContent = "This is a text mail";
        List<String> listRecipients = Arrays.asList(recipients.split(","));
        return ResponseEntity.ok(javaMailService.sendToMany(listRecipients, Message.RecipientType.CC, subject, textContent));
    }

    @PostMapping("/send-to-many-bcc")
    public ResponseEntity<?> sendToManyBCC(String recipients) {
        String subject = "Chào bạn, đây là email thử nghiệm (text)!";
        String textContent = "This is a text mail";
        List<String> listRecipients = Arrays.asList(recipients.split(","));
        return ResponseEntity.ok(javaMailService.sendToMany(listRecipients, Message.RecipientType.BCC, subject, textContent));
    }

}

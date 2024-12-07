//package com.mail.controller;
//
//import com.mail.service.SpringMailService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/spring-mail")
//@RequiredArgsConstructor
//public class SpringMailController {
//
//    private final SpringMailService springMailService;
//
//    @PostMapping("/send-text")
//    public ResponseEntity<?> sendText(String recipient) {
//        String subject = "Chào bạn, đây là spring-email thử nghiệm.";
//        String textContent = "This is a text mail";
//        return ResponseEntity.ok(springMailService.sendText(recipient, subject, textContent));
//    }
//
//}

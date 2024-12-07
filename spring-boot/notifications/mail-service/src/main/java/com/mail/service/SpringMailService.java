//package com.mail.service;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.io.FileSystemResource;
//import org.springframework.mail.MailException;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;
//
//import javax.mail.MessagingException;
//import javax.mail.internet.MimeMessage;
//import java.io.File;
//
//import static com.mail.util.LogUtils.logSendEmailFailed;
//import static com.mail.util.LogUtils.logSendEmailSuccessfully;
//
//@Service
//@RequiredArgsConstructor
//public class SpringMailService {
//
//    private final JavaMailSender javaMailSender;
//
//    @Value("${spring.mail.properties.mail.smtp.from}")
//    private String defaultSenderEmail;
//
//    public void viewMailConfiguration() {
//        if (javaMailSender instanceof JavaMailSenderImpl) {
//            JavaMailSenderImpl mailSender = (JavaMailSenderImpl) javaMailSender;
//            System.out.println("Host: " + mailSender.getHost());
//            System.out.println("Port: " + mailSender.getPort());
//            System.out.println("Username: " + mailSender.getUsername());
//            System.out.println("Password: " + mailSender.getPassword());
//            mailSender.getJavaMailProperties()
//                    .forEach((k, v) -> System.out.println(k + "=" + v));
//        }
//    }
//
//    public boolean sendText(String recipient, String subject, String body) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(defaultSenderEmail);
//        message.setTo(recipient);
//        // message.setTo(new String[]{recipient});
//        message.setSubject(subject);
//        message.setText(body);
//
//        try {
//            javaMailSender.send(message);
//            logSendEmailSuccessfully(recipient);
//            return true;
//        } catch (MailException e) {
//            logSendEmailFailed(e);
//        }
//        return false;
//    }
//
//    public boolean sendHtmlEmail() throws MessagingException {
//        MimeMessage message = javaMailSender.createMimeMessage();
//
//        // message.setFrom(new InternetAddress(PropertiesReader.MAIL_USERNAME));
//        message.setRecipients(MimeMessage.RecipientType.TO, "bathudaide@gmail.com");
//        message.setSubject("Test email from Spring");
//
//        String htmlContent = "<h1>This is a test Spring Boot email</h1>" + //
//                "<p>It can contain <strong>HTML</strong> content.</p>";
//
//        message.setContent(htmlContent, "text/html; charset=utf-8");
//
//        try {
//            javaMailSender.send(message);
//        } catch (MailException ex) {
//            System.err.println(ex.getMessage());
//        }
//        return true;
//    }
//
//    /**
//     * Sending Emails With Attachments
//     */
//    public void sendEmailWithAttachment(String to, String subject, String body) throws Exception {
//        MimeMessage message = javaMailSender.createMimeMessage();
//
//        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//
//        helper.setTo(to);
//        helper.setSubject(subject);
//        helper.setText(body);
//
//        FileSystemResource file = new FileSystemResource(new File("attachment.jpg"));
//        helper.addAttachment("attachment.jpg", file); // <tên file sẽ hiển thị trong mail, file>
//
//        javaMailSender.send(message);
//    }
//
//}

package com.mail.config;

import java.util.Properties;

import javax.mail.PasswordAuthentication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
    - mail.smtp.auth=true
        > JavaMail sẽ sử dụng thông tin xác thực (username và password) được cung
        cấp trong Session để đăng nhập vào máy chủ SMTP trước khi gửi email.

    - mail.smtp.socketFactory.port=456
        > Cổng được sử dụng bởi socket factory để thiết lập một kết SSL/TLS.

    - mail.smtp.starttls.required=true
        > Yêu cầu sử dụng STARTTLS, nếu máy chủ không hỗ trợ STARTTLS, kết nối sẽ bị từ chối.

    - So sánh với mail.smtp.ssl.enable & mail.smtp.starttls.enable
        Thuộc tính      SSL (465)	                                    STARTTLS (587)
        Bảo mật	        Bảo mật ngay từ đầu                             Kết nối bắt đầu không mã hóa, sau đó nâng cấp
        Cấu hình	    mail.smtp.ssl.enable=true	                    mail.smtp.starttls.enable=true
        Cổng mặc định   465	                                            587
        Tương thích	    Thích hợp cho máy chủ yêu cầu SSL ngay từ đầu   Thích hợp cho máy chủ yêu cầu STARTTLS
 */
@Configuration
public class MailConfig {

    @Value("${spring.mail.host}")
    private String smtpHost;

    @Value("${spring.mail.port}")
    private String smtpPort;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${spring.mail.properties.mail.smtp.from}")
    private String senderEmail;

    @Value("${spring.mail.properties.mail.smtp.auth}")
    private String smtpAuth;

    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private String starttls;

    @Value("${spring.mail.properties.mail.smtp.starttls.required}")
    private String requiredTsl;

    @Bean
    public javax.mail.Session javaMailSession() {
        Properties props = new Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", smtpPort);
        props.put("mail.smtp.auth", smtpAuth);
        props.put("mail.smtp.from", senderEmail);

        props.put("mail.smtp.starttls.enable", starttls);
        props.put("mail.smtp.starttls.required", requiredTsl);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        return javax.mail.Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

//    @Bean
//    public JavaMailSender getJavaMailSender() {
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        mailSender.setHost("smtp.gmail.com");
//        mailSender.setPort(587);
//
//        mailSender.setUsername("my.gmail@gmail.com");
//        mailSender.setPassword("password");
//
//        Properties props = mailSender.getJavaMailProperties();
//        props.put("mail.transport.protocol", "smtp");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.debug", "true");
//
//        return mailSender;
//    }

}

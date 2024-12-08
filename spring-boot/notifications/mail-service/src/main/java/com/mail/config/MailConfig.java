package com.mail.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.mail.PasswordAuthentication;
import java.util.Properties;

/*-
    - mail.smtp.auth=true
        > JavaMail sẽ sử dụng thông tin xác thực (username và password) được cung
        cấp trong Session để đăng nhập vào máy chủ SMTP trước khi gửi email.

    - mail.smtp.starttls.required=true
        > Yêu cầu sử dụng STARTTLS, nếu máy chủ không hỗ trợ STARTTLS, kết nối sẽ bị từ chối.

    - So sánh với mail.smtp.ssl.enable & mail.smtp.starttls.enable
        SSL (465)	                        STARTTLS (587)
        Bảo mật ngay từ đầu                 Kết nối bắt đầu không mã hóa, sau đó nâng cấp
        mail.smtp.ssl.enable=true           mail.smtp.starttls.enable=true

    - Gmail mặc định sẽ sử dụng username làm mail-sender (from).
 */
@Configuration
public class MailConfig {

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private String port;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${spring.mail.properties.mail.smtp.from}")
    private String defaultSenderEmail;

    @Bean
    public javax.mail.Session javaMailSession() {
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.from", defaultSenderEmail);

        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");
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

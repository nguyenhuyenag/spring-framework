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

    - So sánh với mail.smtp.ssl.enable & mail.smtp.starttls.enable
        Thuộc tính      SSL (465)	                                    STARTTLS (587)
        Bảo mật	        Bảo mật ngay từ đầu                             Kết nối bắt đầu không mã hóa, sau đó nâng cấp
        Cấu hình	    mail.smtp.ssl.enable=true	                    mail.smtp.starttls.enable=true
        Cổng mặc định   465	                                            587
        Tương thích	    Thích hợp cho máy chủ yêu cầu SSL ngay từ đầu   Thích hợp cho máy chủ yêu cầu STARTTLS
 */
@Configuration
public class MailConfig {

    public static final String MAIL_SENDER = "frompostmail@gmail.com";

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private int port;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    @Bean
    public javax.mail.Session javaMailSession() {
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.from", MAIL_SENDER);
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.ssl.enable", true);

        // Old config
        // props.put("mail.smtp.starttls.enable", "true");
        // props.put("mail.smtp.socketFactory.port", "465"); // Cổng cho socket factory
        // props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); // Sử dụng SSLSocketFactory

        return javax.mail.Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

//	@Bean
//	public javax.mail.Session javaMailSession() {
//		Properties props = new Properties();
//		props.put("mail.smtp.host", hostname);
//		props.put("mail.smtp.port", port);
//		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.from", fromHostName);
//
//		// props.put("mail.smtp.socketFactory.port", port);
//
//		if (useSSL) {
//			props.put("mail.smtp.ssl.trust", hostname);
//			props.put("mail.smtp.ssl.enable", "true");
//		} else {
//			props.put("mail.smtp.starttls.enable", "true");
//			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//		}
//
//		return javax.mail.Session.getInstance(props, new javax.mail.Authenticator() {
//			protected PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication(username, password);
//			}
//		});
//	}

}

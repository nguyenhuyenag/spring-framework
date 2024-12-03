package com.mail.config;

import java.util.Properties;

import javax.mail.PasswordAuthentication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        // props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
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

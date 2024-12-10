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

    - Timeout cho SMTP giúp đảm bảo rằng các thread hoặc tài nguyên của bạn không bị giữ lại
    quá lâu nếu có sự cố như:
        + SMTP server không phản hồi.
        + Kết nối mạng kém hoặc bị gián đoạn.
        + Server gửi email bị quá tải.

        + Các giá trị cấu hình hợp lý là 5-10 giây
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

    // Giới hạn thời gian để thiết lập kết nối, nếu quá thời gian -> java.net.SocketTimeoutException
    @Value("${spring.mail.properties.mail.smtp.connectiontimeout}")
    private String connectionTimeout;

    // Thời gian chờ phản hồi từ server
    @Value("${spring.mail.properties.mail.smtp.timeout}")
    private String readTimeout;

    // Thời gian chờ để ghi dữ liệu email
    @Value("${spring.mail.properties.mail.smtp.writetimeout}")
    private String writeTimeout;

    @Bean
    public javax.mail.Session javaMailSession() {
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.from", defaultSenderEmail);

        // SSL configurations
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        // Timeout configurations
        props.put("mail.smtp.connectiontimeout", connectionTimeout);
        props.put("mail.smtp.timeout", readTimeout);
        props.put("mail.smtp.writetimeout", writeTimeout);

        return javax.mail.Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

}

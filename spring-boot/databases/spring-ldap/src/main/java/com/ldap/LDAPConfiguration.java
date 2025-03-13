//package com.ldap;
//
//import lombok.extern.slf4j.Slf4j;
//import org.forgerock.opendj.ldap.Connection;
//import org.forgerock.opendj.ldap.LDAPConnectionFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
//@Slf4j
//@Component
//public class LDAPConfiguration {
//
//    @Value("${spring.ldap.username}")
//    private String username;
//
//    @Value("${spring.ldap.password}")
//    private String password;
//
//    @Bean
//    public Connection connection() {
//        try (LDAPConnectionFactory factory = new LDAPConnectionFactory("192.168.0.98", 389)) {
//            Connection connection = factory.getConnection();
//            connection.bind(username, password.toCharArray());
//            return connection;
//        } catch (Exception e) {
//            log.error("Failed to establish LDAP connection: {}", e.getMessage(), e);
//        }
//        return null;
//    }
//
//}

//package com.ldap;
//
//import org.forgerock.opendj.ldap.Connection;
//import org.forgerock.opendj.ldap.ErrorResultException;
//import org.forgerock.opendj.ldap.LDAPConnectionFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.event.ApplicationReadyEvent;
//import org.springframework.context.event.EventListener;
//import org.springframework.stereotype.Component;
//import org.springframework.util.SerializationUtils;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.PreDestroy;
//
//@Component
//public class LDAPUtils {
//
//    @Value("${spring.ldap.username}")
//    private String username;
//
//    @Value("${spring.ldap.password}")
//    private String password;
//
//    private static final LDAPConnectionFactory factory = new LDAPConnectionFactory("192.168.0.98", 389);
//    // private static Connection connection;
//
////    @PostConstruct
////    private void init() throws ErrorResultException {
////        connection = factory.getConnection(); // Initialize connection
////        if (connection != null) {
////            connection.bind(username, password.toCharArray());
////        } else {
////            throw new IllegalStateException("Failed to obtain LDAP connection");
////        }
////    }
//
//    public static Connection getConnection() {
//        try {
//            Connection connection = factory.getConnection();
//            connection.bind(username, password.toCharArray());
//        } catch (ErrorResultException e) {
//
//        }
//        return null;
//    }
//
//    @PreDestroy
//    public void destroy() {
//        factory.close();
//    }
//}
//

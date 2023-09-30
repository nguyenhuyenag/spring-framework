//package com.session;
//
//import javax.servlet.http.HttpSessionEvent;
//import javax.servlet.http.HttpSessionListener;
//
//public class MySessionListener implements HttpSessionListener {
//
//    @Override
//    public void sessionCreated(HttpSessionEvent se) {
//        // Sự kiện khi session được tạo ra
//        System.out.println("Session created: " + se.getSession().getId());
//        // Thêm logic xử lý tại đây nếu cần
//    }
//
//    @Override
//    public void sessionDestroyed(HttpSessionEvent se) {
//        // Sự kiện khi session bị hủy
//        System.out.println("Session destroyed: " + se.getSession().getId());
//        // Thêm logic xử lý tại đây nếu cần
//    }
//    
//}
//

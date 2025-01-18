package com.util;

import javax.servlet.http.HttpServletRequest;

public class Authentication {

    public static boolean authentication(HttpServletRequest request) {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        return (!"abc".equals(userName) || !"123".equals(password));
    }

}

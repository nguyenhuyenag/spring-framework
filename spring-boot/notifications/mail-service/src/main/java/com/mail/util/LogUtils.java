package com.mail.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtils {

    public static void logSendEmailSuccessfully(String recipient) {
        Logger log = LoggerFactory.getLogger(getCallerClassName());
        log.info("Email sent successfully to: {}", recipient);
    }

    public static void logSendEmailFailed(Exception e) {
        Logger log = LoggerFactory.getLogger(getCallerClassName());
        log.error("Failed to send email. Error: {}. Exception: ", e.getMessage());
    }

    private static String getCallerClassName() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (int i = 1; i < stackTrace.length; i++) {
            String className = stackTrace[i].getClassName();
            // Bỏ qua LogUtils và các lớp JVM liên quan
            if (!className.equals(LogUtils.class.getName()) && !className.startsWith("java.lang.Thread")) {
                return className;
            }
        }
        return LogUtils.class.getName(); // Fallback
    }

}

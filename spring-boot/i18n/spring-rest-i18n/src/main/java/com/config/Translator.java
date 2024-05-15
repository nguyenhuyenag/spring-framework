package com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class Translator {

    private static ResourceBundleMessageSource messageSource;

    private Translator(@Autowired ResourceBundleMessageSource messageSource) {
        Translator.messageSource = messageSource;
    }

    public static String getMessage(String msgCode, Object...formatValue) {
        Locale locale = LocaleContextHolder.getLocale();
        // return messageSource.getMessage(msgCode, null, locale);
        return messageSource.getMessage(msgCode, formatValue, locale);
    }
}
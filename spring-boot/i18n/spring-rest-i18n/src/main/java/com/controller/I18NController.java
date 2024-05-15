package com.controller;

import com.config.Translator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

@RestController
@RequestMapping("/i18n")
public class I18NController {

    /*
        headers = {
          'Content-Type': 'application/json;charset=utf-8',
          'Accept-Language': 'en-US'
        }
     */
    @GetMapping("/greet")
    public ResponseEntity<?> getMessages(String name) {
        Map<String, String> data = new LinkedHashMap<>();
        Locale locale = LocaleContextHolder.getLocale();
        String message = Translator.getMessage("hello.username", name);
        data.put("locale", locale.getDisplayName());
        data.put("message", message);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/list-locales")
    public ResponseEntity<?> listLocales() {
        Map<String, String> data = new TreeMap<>();

        // Lấy danh sách các ngôn ngữ được hỗ trợ trong Java
        Locale[] availableLocales = Locale.getAvailableLocales();

        // In ra mã ngôn ngữ và tên ngôn ngữ tương ứng
        for (Locale locale : availableLocales) {
            if (StringUtils.isNotEmpty(locale.getLanguage())) {
                // data.put(locale.getLanguage(), locale.getDisplayName());
                data.put(StringUtils.replace(locale.toString(), "_", "-"), locale.getDisplayName());
            }
        }

        return ResponseEntity.ok(data);
    }

}

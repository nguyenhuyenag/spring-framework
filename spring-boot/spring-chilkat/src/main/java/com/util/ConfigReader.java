package com.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:config.properties", encoding = "utf-8")
public class ConfigReader {

    public static String CHILKAT_LICENSE_KEY;

    @Value("${chilkat.license-key}")
    public void setChilkatLicenseKey(String key) {
        ConfigReader.CHILKAT_LICENSE_KEY = key;
    }

}

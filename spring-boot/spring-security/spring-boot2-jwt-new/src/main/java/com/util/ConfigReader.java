package com.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:config.properties", encoding = "utf-8")
public class ConfigReader {

    public static String JWT_SECRET_KEY;
    public static String JWT_ISSUER;
    public static long JWT_EXPIRATION_TIME;

    @Value("${JWT_SECRET_KEY}")
    public void setJwtSecretKey(String value) {
        ConfigReader.JWT_SECRET_KEY = value;
    }

    @Value("${JWT_ISSUER}")
    public void setJwtIssuer(String value) {
        ConfigReader.JWT_ISSUER = value;
    }

    @Value("${JWT_EXPIRATION_TIME}")
    public void setJwtExpirationTime(long value) {
        ConfigReader.JWT_EXPIRATION_TIME = value;
    }

}

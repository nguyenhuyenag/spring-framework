package com.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:datasource.properties")
// @PropertySource("classpath:application.properties")
public class PropertiesReader {

}

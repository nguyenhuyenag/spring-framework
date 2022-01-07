package com.config;

import java.util.TimeZone;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class JacksonConfiguration {

//    @Autowired
//    public JacksonConfiguration(ObjectMapper objectMapper){
//        objectMapper.setTimeZone(TimeZone.getDefault());
//    }
	
	@Bean
	ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
	    return builder.createXmlMapper(false)
	            // Set timezone for JSON serialization as system timezone
	            .timeZone(TimeZone.getDefault())
	            .build();
	}
    
}

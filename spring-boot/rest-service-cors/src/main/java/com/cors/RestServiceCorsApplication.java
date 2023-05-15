package com.cors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RestServiceCorsApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestServiceCorsApplication.class, args);
	}

	@Bean
	FilterRegistrationBean<CORSFilter> filterRegistrationBean() {
		FilterRegistrationBean<CORSFilter> filter = new FilterRegistrationBean<>();
		filter.setFilter(new CORSFilter());
		filter.setName("CORS Filter");
		// filter.addUrlPatterns("/*");
		filter.setOrder(1);
		return filter;
	}

}

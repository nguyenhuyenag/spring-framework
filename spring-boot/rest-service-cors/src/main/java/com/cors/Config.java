package com.cors;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

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

package com.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	/**
	 * Spring Boot allows configuring Content Negotiation using properties
	 */
	@Override
	public void configureContentNegotiation(final ContentNegotiationConfigurer configurer) {
		configurer.favorParameter(true) //
				.ignoreAcceptHeader(false) //
				.parameterName("mediaType") //
				.useRegisteredExtensionsOnly(false) //
				.defaultContentType(MediaType.APPLICATION_JSON) //
				.mediaType("xml", MediaType.APPLICATION_XML) //
				.mediaType("json", MediaType.APPLICATION_JSON);
	}

}

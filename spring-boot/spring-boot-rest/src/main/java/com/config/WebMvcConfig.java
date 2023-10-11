package com.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2CollectionHttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	/**
	 * Spring Boot allows configuring Content Negotiation using properties
	 */
	@Override
	public void configureContentNegotiation(final ContentNegotiationConfigurer configurer) {
		configurer.favorParameter(true) //
				.parameterName("mediaType") //
				// .ignoreAcceptHeader(false) //
				// .useRegisteredExtensionsOnly(false) //
				.defaultContentType(MediaType.APPLICATION_JSON) //
				.mediaType("json", MediaType.APPLICATION_JSON)
				.mediaType("xml", MediaType.APPLICATION_XML) //
				;
	}
	
	@Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(0, new Jaxb2CollectionHttpMessageConverter<>());
        converters.add(0, new Jaxb2RootElementHttpMessageConverter());
        // System.out.println("Converters:" + converters);
    }

}

package com.soap;

import java.util.List;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.server.endpoint.interceptor.PayloadLoggingInterceptor;
import org.springframework.ws.server.endpoint.mapping.PayloadRootQNameEndpointMapping;
import org.springframework.ws.soap.server.endpoint.mapping.SoapActionEndpointMapping;
import org.springframework.ws.transport.http.MessageDispatcherServlet;

@EnableWs
@Configuration
public class WsConfig extends WsConfigurerAdapter {

	@Bean // ("helloWorldService")
	public HelloWorldService helloWorldService() {
		return new HelloWorldServiceImpl();
	}

	@Bean
	public SimpleMappingExceptionResolver exceptionResolver() {
		SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
		Properties mappings = new Properties();
		mappings.setProperty(Exception.class.getName(), "SOAP-ENV:Server");
		resolver.setExceptionMappings(mappings);
		return resolver;
	}

	@Bean
	public EndpointInterceptor payloadLoggingInterceptor() {
		return new PayloadLoggingInterceptor();
	}

	@Override
	public void addInterceptors(List<EndpointInterceptor> interceptors) {
		interceptors.add(payloadLoggingInterceptor());
	}

	@Bean
	public PayloadRootQNameEndpointMapping payloadRootQNameEndpointMapping() {
		PayloadRootQNameEndpointMapping endpointMapping = new PayloadRootQNameEndpointMapping();
		endpointMapping.setDefaultEndpoint(helloWorldService());
		return endpointMapping;
	}

	@Bean
	public SoapActionEndpointMapping soapActionEndpointMapping() {
		SoapActionEndpointMapping endpointMapping = new SoapActionEndpointMapping();
		// endpointMapping.setUseActionAsEndpointKey(true);
		endpointMapping.setDefaultEndpoint(helloWorldService());
		return endpointMapping;
	}

	@Bean
	public MessageDispatcherServlet messageDispatcherServlet() {
		return new MessageDispatcherServlet();
	}

}
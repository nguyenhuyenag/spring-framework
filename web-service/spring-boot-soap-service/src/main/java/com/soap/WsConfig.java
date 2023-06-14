package com.soap;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WsConfig extends WsConfigurerAdapter {
	
	// targetNamespace="http://www.howtodoinjava.com/xml/school" in .xsd
	public static final String NAMESPACE_URI = "http://www.howtodoinjava.com/xml/school";

	@Bean
	public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(
			ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setTransformWsdlLocations(true);
		servlet.setApplicationContext(applicationContext);
		return new ServletRegistrationBean<>(servlet);
	}
	
	@Bean
	public XsdSchema studentSchema() {
		return new SimpleXsdSchema(new ClassPathResource("/wsdl/school.xsd"));
	}

	@Bean(name = "student-details") // -> http://localhost:8080/student-details.wsdl
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema schema) {
		DefaultWsdl11Definition wsdl = new DefaultWsdl11Definition();
		wsdl.setPortTypeName("StudentDetailsPort");
		wsdl.setTargetNamespace(NAMESPACE_URI);
		wsdl.setSchema(schema);
		return wsdl;
	}
	
}
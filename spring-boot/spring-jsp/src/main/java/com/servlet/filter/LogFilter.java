package com.servlet.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/*-
 * Servlet-Filter URL Pattern
 * 
 * 		URL Pattern						Ví dụ
 *		
 *		/*							http://example.com/contextPath
 *									http://example.com/contextPath/status/abc
 *
 *		/status/abc/*				http://example.com/contextPath/status/abc
 *									http://example.com/contextPath/status/abc/mnp
 *									http://example.com/contextPath/status/abc/mnp?date=today
 *									http://example.com/contextPath/test/abc/mnp		(SAI)
 *
 *		*.map						http://example.com/contextPath/status/abc.map
 *									http://example.com/contextPath/status.map?date=today
 *									http://example.com/contextPath/status/abc.MAP	(SAI)
 */
@Component
public class LogFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) //
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String servletPath = req.getServletPath();
		System.out.println("#INFO " + new Date() + " - ServletPath :" + servletPath + ", URL =" + req.getRequestURL());
		// Cho phép request được đi tiếp (vượt qua Filter này)
		chain.doFilter(request, response);
	}

	@Bean
	public FilterRegistrationBean<LogFilter> loggingFilter() {
		FilterRegistrationBean<LogFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new LogFilter());
		registrationBean.addUrlPatterns("/core/*");
		return registrationBean;
	}

}

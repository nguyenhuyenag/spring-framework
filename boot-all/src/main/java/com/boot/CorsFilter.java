package com.boot;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@WebFilter("/*")
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

	private final Logger LOG = LoggerFactory.getLogger(CorsFilter.class);

	public CorsFilter() {
		LOG.info("CorsFilter init...");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) //
			throws IOException, ServletException {
		final HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.setHeader("Access-Control-Allow-Origin", "*");
		httpResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
		httpResponse.setHeader("Access-Control-Allow-Headers", //
				"Origin, X-Requested-With, Content-Type, Accept, Authorization");
		httpResponse.setHeader("Access-Control-Max-Age", "3600");
		httpResponse.setHeader("Content-Type", "application/json; charset=utf-8");
		httpResponse.setHeader("Access-Control-Expose-Headers", "Authorization");

		if ("OPTIONS".equalsIgnoreCase(((HttpServletRequest) request).getMethod())) {
			httpResponse.setStatus(HttpServletResponse.SC_OK);
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {

	}

	@Override
	public void init(FilterConfig config) throws ServletException {

	}
}

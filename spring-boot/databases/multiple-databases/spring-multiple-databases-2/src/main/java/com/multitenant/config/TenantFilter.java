package com.multitenant.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Component
public class TenantFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String tenantName = req.getHeader("X-TenantID");
		if (StringUtils.isNoneEmpty(tenantName)) {
			TenantContext.setCurrentTenant(tenantName);
		}
		try {
			chain.doFilter(request, response);
		} finally {
			// Resetting the current tenant after processing the request
			TenantContext.clearTenant();
		}
	}

}

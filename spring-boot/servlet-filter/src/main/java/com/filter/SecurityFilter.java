package com.filter;

import java.io.IOException;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.model.User;
import com.request.UserRoleRequestWrapper;
import com.util.SecurityUtils;
import com.util.WebUtils;

@Component
public class SecurityFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) //
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String servletPath = req.getServletPath();
		// Thông tin người dùng đã được lưu trong Session
		User user = WebUtils.getUserFromSession(req.getSession());
		if (servletPath.equals("/login")) {
			chain.doFilter(req, res);
			return;
		}
		HttpServletRequest requestWrapper = req;
		if (user != null) {
			String username = user.getUsername();
			// Các vai trò (Role)
			Set<String> roles = user.getRoles();
			// Gói request cũ bởi một Request mới với các thông tin username và Roles
			requestWrapper = new UserRoleRequestWrapper(req, username, roles);
		}

		// Các trang bắt buộc phải đăng nhập
		if (SecurityUtils.isProtectedPage(servletPath)) {
			// Nếu chưa đăng nhập
			if (user == null) {
				String requestUri = req.getRequestURI();
				// Lưu trữ trang hiện tại để redirect đến sau khi đăng nhập thành công
				String redirectKey = WebUtils.storeRedirectUrlAfterLogin(requestUri);
				res.sendRedirect(requestWrapper.getContextPath() + "/login?redirectId=" + redirectKey);
				return;
			}
			// Kiểm tra người dùng có vai trò hợp lệ hay không?
			boolean hasPermission = SecurityUtils.hasPermission(requestWrapper);
			if (!hasPermission) {
				res.sendRedirect(requestWrapper.getContextPath() + "/accessDenied");
				return;
			}
		}
		chain.doFilter(requestWrapper, res);
	}

}

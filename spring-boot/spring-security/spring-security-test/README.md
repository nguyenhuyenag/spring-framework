# Session Manager

	- IF_REQUIRED: A session will be created only if required (default)
	- ALWAYS: A session will always be created if one doesn’t already exist
	- NEVER: The framework will never create a session itself, but it will use one if it already exists (nếu không có phiên nào tồn tại, yêu cầu sẽ bị từ chối).
	- STATELESS: Không có phiên làm việc nào được tạo hoặc sử dụng. Thường dùng cho các ứng dụng RESTful (không lưu trạng thái), trong đó mỗi yêu cầu độc lập và không cần phiên làm việc
	
# Session timeout

	server.servlet.session.timeout=30s
	
	- Session timeout > Remember me

# Thêm CSRF vào JSP

	<meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>

# Phân quyền

	.antMatchers("/admin").hasRole("ADMIN")
						.hasAuthority("ROLE_ADMIN")
						.access("hasRole('ROLE_ADMIN')")
						.access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
						
	- Ta có: hasRole() = hasAuthority('ROLE_' + role)


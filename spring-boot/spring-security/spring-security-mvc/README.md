# Session Manager

	- ALWAYS (LUÔN LUÔN): Một phiên làm việc mới sẽ luôn luôn được tạo cho mỗi yêu cầu, bất kể người dùng đã được xác thực hay chưa.

	- NEVER (KHÔNG BAO GIỜ): Spring Security sẽ không bao giờ tạo một phiên làm việc mới. Nó sẽ dựa vào phiên làm việc hiện tại, và nếu không có phiên nào tồn tại, yêu cầu sẽ bị từ chối.

	- IF_REQUIRED (NẾU CẦN): Một phiên làm việc mới sẽ được tạo chỉ khi yêu cầu đòi hỏi điều đó, ví dụ khi người dùng chưa được xác thực.

	- STATELESS (KHÔNG LƯU TRẠNG THÁI): Không có phiên làm việc nào được tạo hoặc sử dụng. Chính sách này thích hợp cho các ứng dụng RESTful không lưu trạng thái, trong đó mỗi yêu cầu độc lập và không cần phiên làm việc

# Thêm CSRF vào JSP

	<meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>

# Phân quyền

	.antMatchers("/admin").hasRole("ADMIN")
						.hasAuthority("ROLE_ADMIN")
						.access("hasRole('ROLE_ADMIN')")
						.access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
						
	- Ta có: hasRole() = hasAuthority('ROLE_' + role)

# Session timeout

	server.servlet.session.timeout=30s
	
	- Session timeout > Remember me

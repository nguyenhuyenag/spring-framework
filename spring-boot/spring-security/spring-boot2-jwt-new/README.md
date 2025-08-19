# Spring Boot JWT

- Spring Security

	To call the method by any of the role mentioned use
		
		@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_TIMER_TASK')")
	
	and enable pre- and post- annotations in security class
		
		@EnableGlobalMethodSecurity(prePostEnabled = true)

- Authentication (Xác thực người dùng) và Authorization (Ủy quyền truy cập vào tài nguyên)
		
- Không nên lưu list role vào token, nên truy vấn database để lấy role mỗi khi authentication thành công?

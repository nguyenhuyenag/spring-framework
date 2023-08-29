# Thêm CSRF vào JSP

	<meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>

# Phân quyền

	.antMatchers("/admin").hasRole("ADMIN")
						.hasAuthority("ROLE_ADMIN")
						.access("hasRole('ROLE_ADMIN')")
						.access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
						
	- Ta có: hasRole() = hasAuthority('ROLE_' + role)
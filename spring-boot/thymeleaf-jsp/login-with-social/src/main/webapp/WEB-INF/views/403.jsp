<!DOCTYPE html>
<html>
<head>
	<title>Spring Boot Security</title>
</head>
<body>
	<h2>Access Denied Page - 403</h2>
	<h3>Hi: ${request.userPrincipal.name} - you do not have permission to access this page</h3>
	<a href="/user">User Page</a>
	<br />
	<br />
	<form action="/j_spring_security_logout" method="POST">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<input type="submit" value="Logout" />
	</form>
</body>
</html>

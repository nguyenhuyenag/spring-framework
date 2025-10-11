<!DOCTYPE html>
<html>
<head>
	<title>Spring Boot Security Hello</title>
</head>
<body>
	<h2>Admin Page</h2>
	<h3>Hello: ${request.userPrincipal.name}</h3>
	<a href="/user">User Page</a>
	<br />
	<br />
	<form action="/j_spring_security_logout" method="POST">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<input type="submit" value="Logout" />
	</form>
</body>
</html>

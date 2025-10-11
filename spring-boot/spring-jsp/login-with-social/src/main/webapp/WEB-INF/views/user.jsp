<!DOCTYPE html>
<html>
<head>
	<title>Spring Boot Security</title>
</head>
<body>
	<h2>User Page</h2>
	<h3>Welcome: ${request.userPrincipal.name}</h3>
	<a href="/admin">Admin Page</a>
	<br />
	<br />
	<form action="/j_spring_security_logout" method="POST">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<input type="submit" value="Logout" />
	</form>
</body>
</html>

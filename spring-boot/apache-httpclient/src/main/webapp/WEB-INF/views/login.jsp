<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>Social Login</title>
</head>
<body>
	<h2>Spring Boot Security Social Login</h2>
	<a href="${URL_GOOGLE}">Login With Gmail</a>
	<br/>
	<a href="https://www.facebook.com/dialog/oauth?client_id=180439422588509&redirect_uri=https://localhost:8443/login-facebook">Login With Facebook</a>
	<br />
	<form name='login-form' action="/j_spring_security_login" method='POST'>
		<table>
			<tr>
				<td>Username:</td>
				<td><input type='text' name='username' value='user'></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='password' value="123" /></td>
			</tr>
			<tr>
				<td><input name="submit" type="submit" value="submit" /></td>
			</tr>
		</table>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
</body>
</html>

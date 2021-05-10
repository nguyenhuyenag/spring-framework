<!DOCTYPE html>
<html>
<head>
	<title>Login Google</title>
</head>
<body>
	<h2>Spring Boot Security Login with Google+</h2>
	<a href="https://accounts.google.com/o/oauth2/auth?scope=email&approval_prompt=force&response_type=code&redirect_uri=${HOSTNAME}&client_id=${CLIENT_ID}">
		Login With Gmail
	</a>
	<br />
	<form name='login-form' action="j_spring_security_login" method='POST'>
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
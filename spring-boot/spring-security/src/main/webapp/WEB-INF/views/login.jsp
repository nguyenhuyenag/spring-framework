<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>Login</title>
</head>
<body>
	<%@ include file="menu.jsp" %>
	<h1>Login</h1>

	<form name='f' action="${CONTEXT_PATH}/j_spring_security_check" method='POST'>
		<table>
			<tr>
				<td>User:</td>
				<td><input type='text' name='username' value='user'></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='password' value='123' /></td>
			</tr>
			<tr>
				<td>Remember Me?</td>
				<td><input type="checkbox" name="remember-me" /></td>
			</tr>
			<tr>
				<td><input name="submit" type="submit" value="submit" /></td>
			</tr>
		</table>
		<!-- <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> -->
	</form>

	<br> Username/password:
	<ul>
		<li>user/123</li>
		<li>admin/123</li>
	</ul>

</body>

</html>

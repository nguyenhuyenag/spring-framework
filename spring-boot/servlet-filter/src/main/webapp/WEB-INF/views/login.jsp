<!DOCTYPE html>
<html>
<head>
	<title>Login</title>
	<meta charset="UTF-8">
	<link rel="shortcut icon" href="#">
</head>
<body>
	<jsp:include page="menu.jsp"></jsp:include>
	<h3>Login Page</h3>
	<p style="color: red;">${errorMessage}</p>
	<form method="POST" action="${pageContext.request.contextPath}/login">
		<input type="hidden" name="redirectId" value="${param.redirectId}" />
		<table>
			<tr>
				<td>User Name</td>
				<td><input type="text" name="username" value="employee1" />
				</td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" name="password" value="123" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Submit" />
					<a href="${pageContext.request.contextPath}/">Cancel</a>
				</td>
			</tr>
		</table>
	</form>
	<p style="color: blue;">Login with:</p>
	employee1/123
	<br>
	manager1/123
</body>
</html>

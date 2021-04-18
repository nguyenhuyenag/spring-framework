<html>
<head>
	<meta charset="UTF-8" />
	<link rel="shortcut icon" href="#">
	<title>Remember Me with Spring MVC Framework</title>
</head>
<body>
	Welcome ${sessionScope.username }
	<br>
	<a href="${pageContext.request.contextPath}/logout">Logout</a>
</body>
</html>

<html>
<head>
	<meta charset="UTF-8" />
	<link rel="shortcut icon" href="#">
	<title>Spring MVC Framework</title>
</head>
<body>
	Hello: <%=session.getAttribute("username")%>
	<%-- Hello: ${sessionScope.username} --%>
	<br>
	<%-- <a href="${pageContext.request.contextPath}/logout">Logout</a> --%>
</body>
</html>

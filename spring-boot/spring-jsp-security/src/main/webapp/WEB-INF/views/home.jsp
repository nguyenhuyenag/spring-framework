<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
	<title>Home Page</title>
	<link rel="shortcut icon" href="#">
	<c:set var="CONTEXT_PATH" value="${pageContext.request.contextPath}" scope="session" />
</head>

<body>
	<%@ include file="menu.jsp" %>
	<h2><span>${message}</span></h2>
</body>

</html>

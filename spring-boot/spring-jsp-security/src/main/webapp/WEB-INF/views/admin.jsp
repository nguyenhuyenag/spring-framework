<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>Admin Page</title>
	<link rel="shortcut icon" href="#">
</head>
<body>
	<%@ include file="menu.jsp" %>

	<h2>Admin Page</h2>
	<h3>
		Welcome: <span>${pageContext.request.userPrincipal.name}</span>
	</h3>
	<b>This is protected page!</b>

	<br />
	<br />

	<c:if test="${userInfo != null}">
		<div>${userInfo}</div>
	</c:if>
</body>

</html>


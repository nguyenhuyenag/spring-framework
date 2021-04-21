<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>
<head>
	<title>User Info</title>
</head>
<body>
	<!-- Include _menu.html -->
	<%@ include file="menu.jsp"%>

	<h2>User Info Page</h2>
	<h3>
		Welcome : <span>${request.userPrincipal.name}</span>
	</h3>
	<b>This is protected page!</b>

	<br />
	<br />

	<c:if test="${userInfo != null}">
		<div>${userInfo}</div>	
	</c:if>
</body>

</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<head>
	<title>UserInfo</title>
	<link rel="shortcut icon" href="#">
</head>

<div>
	<h2>User Info</h2>
	<h3>
		Welcome: <span>${pageContext.request.userPrincipal.name}</span>
	</h3>
	<b>This is protected page!</b>

	<br />
	<br />

	<p>
		Hello: <b><c:out value="${pageContext.request.remoteUser}"/></b><br>
		Roles: <b><sec:authentication property="principal.authorities" /></b>
	  </p>

	<c:if test="${userInfo != null}">
		<div>${userInfo}</div>
	</c:if>
</div>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div style="border: 1px solid #ccc; padding: 5px; margin-bottom: 20px;">
	<a href="/home">Home</a>
		&nbsp; | &nbsp;
	<a href="/user-info">User Info</a>
		&nbsp; | &nbsp; 
	<a href="/admin">Admin</a>
		&nbsp; | &nbsp;
	<a href="/security-taglib">Taglib</a>
		&nbsp; | &nbsp;
	<%-- <c:if test="${pageContext.request.userPrincipal == null}">
		<a href="/login">Login</a>
		| &nbsp;
	</c:if>
	<c:if test="${pageContext.request.userPrincipal != null}">
		<a href="/logout">Logout</a>
		| Welcome: <strong>${pageContext.request.userPrincipal.name}</strong>
	</c:if> --%>
	<sec:authorize access="!isAuthenticated()">
		<a href="/login">Login</a>
		&nbsp; | &nbsp;
	</sec:authorize> 
	<sec:authorize access="isAuthenticated()">
		<a href="/logout">Logout</a>
		&nbsp; | &nbsp;
		Welcome: <strong>${pageContext.request.userPrincipal.name}</strong>
	</sec:authorize>
</div>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div style="border: 1px solid #ccc; padding: 5px; margin-bottom: 20px;">
	<a href="/">Home</a>
		| &nbsp;
	<a href="/user-info">User Info</a>
		| &nbsp;
	<a href="/admin">Admin</a>
		| &nbsp;
	<c:if test="${pageContext.request.userPrincipal == null}">
		<a href="/login">Login</a>
		| &nbsp;
	</c:if>
	<c:if test="${pageContext.request.userPrincipal != null}">
		<a href="/logout">Logout</a>
	</c:if>
</div>

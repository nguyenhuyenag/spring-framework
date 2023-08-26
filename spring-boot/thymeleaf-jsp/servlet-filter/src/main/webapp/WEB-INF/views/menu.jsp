<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<a href="${pageContext.request.contextPath}/home">Home</a>
||
<a href="${pageContext.request.contextPath}/employee">Employee</a>
||
<a href="${pageContext.request.contextPath}/manager">Manager</a>
<c:choose>
	<c:when test="${empty loginedUser}">
		||
		<a href="${pageContext.request.contextPath}/login">Login</a>
	</c:when>
	<c:otherwise>
		||
		<a href="${pageContext.request.contextPath}/userInfo">User Info</a>
		||
		<a href="${pageContext.request.contextPath}/logout">Logout</a>
		&nbsp;
		<span style="color: red">[&nbsp;${loginedUser.username}&nbsp;]</span>
	</c:otherwise>
</c:choose>

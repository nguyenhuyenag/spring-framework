<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<style>
	a {
		padding: 5px;
	}
</style>

<sec:authorize access="isAuthenticated()">
	<div style="border: 1px solid #ccc; padding: 5px; margin-bottom: 20px;">
		<sec:authorize access="isAuthenticated()">
			<a href="/home">Home</a> |
			<a href="/user-info">UserInfo</a> |
		</sec:authorize>
		<a href="/security-taglib">Taglib</a> |
		<a href="/page-abcdf">404 Error</a> |
		<sec:authorize access="isAuthenticated">
			<a href="/edit-user">Edit User</a> |
			<a href="/admin">Admin</a> |
		</sec:authorize>
		<sec:authorize access="!isAuthenticated()">
			<a href="/login">Login</a> |
		</sec:authorize> 
		<sec:authorize access="isAuthenticated()">
			<a href="/logout">Logout</a> |
    		Welcome: <strong><sec:authentication property="name"/></strong>
			<%-- Welcome: <strong>${pageContext.request.userPrincipal.name}</strong> --%>
		</sec:authorize>
	</div>
</sec:authorize>

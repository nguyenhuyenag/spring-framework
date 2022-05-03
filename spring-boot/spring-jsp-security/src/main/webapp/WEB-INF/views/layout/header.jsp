<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<sec:authorize access="isAuthenticated()">
	<div style="border: 1px solid #ccc; padding: 5px; margin-bottom: 20px;">
		<sec:authorize access="isAuthenticated()">
			<a href="/home">Home</a> &nbsp; | &nbsp; 
			<a href="/user-info">UserInfo</a> &nbsp; | &nbsp; 
		</sec:authorize>
		<a href="/security-taglib">Taglib</a> &nbsp; | &nbsp;
		<a href="/page-abcdf">404 Error</a> &nbsp; | &nbsp;
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<a href="/admin">Admin</a> &nbsp; | &nbsp;
		</sec:authorize>
		<sec:authorize access="!isAuthenticated()">
			<a href="/login">Login</a> &nbsp; | &nbsp;
		</sec:authorize> 
		<sec:authorize access="isAuthenticated()">
			<a href="/logout">Logout</a> &nbsp; | &nbsp;
			Welcome: <strong>${pageContext.request.userPrincipal.name}</strong>
		</sec:authorize>
	</div>
</sec:authorize>
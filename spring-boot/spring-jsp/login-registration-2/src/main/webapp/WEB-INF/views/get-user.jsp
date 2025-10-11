<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<div class="container">
	<h1 class="text-center mt-4">Retrieve User Information in Spring Security</h1>
	<h2 class="text-center mt-4">
		Username: ${username} <br/>
		<security:authorize access="isAuthenticated()">
			Authenticated as <security:authentication property="principal.username" /> 
		</security:authorize>
	</h2>
</div>

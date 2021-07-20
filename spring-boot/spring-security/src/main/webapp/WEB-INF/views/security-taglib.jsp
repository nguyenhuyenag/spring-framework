<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
	<title>Security Taglib</title>
	<link rel="shortcut icon" href="#">
</head>
<body>
	<%@ include file="menu.jsp" %>
	
	<h2>Spring Security - Taglib</h2>
	
	<h3>1) The authorize Tag</h3>
	
	<%--
	
		<sec:authorize access="hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')" var="isAuthenticated">
			// todo
	    </sec:authorize>
	    
	    <sec:authorize access="hasAnyRole('ROLE_ADMIN')">
	    	<a href="delete/${file.id}">Delete</a>
		</sec:authorize>
	
	    isAuthenticated: <c:out value="${isAuthenticated}"/>
	    
		<sec:authorize access="isAuthenticated()">Logout</sec:authorize>
		
		<sec:authorize access="hasRole('ADMIN')">Hello Admin</sec:authorize>
	
	--%>
	
	<ul class="list">
		<li><p><b>hasRole("ADMIN")</b>: Evaluates to true if the current user has the admin role.</p></li>
		<li><p><b>hasAnyRole("ADMIN", "USER")</b>: Evaluates to true if the current user has any of the listed roles</p></li>
		<li><p><b>isAnonymous()</b>: Evaluates to true if the current user is an anonymous user</p></li>
		<li><p><b>isRememberMe()</b>: Evaluates to true if the current user is a remember-me user</p></li>
		<li><p><b>isFullyAuthenticated()</b>: Evaluates to true if the user is authenticated and is neither anonymous nor a remember-me user</p></li>
	</ul>
	
	<sec:authorize url="/security-taglib">
   		This content will only be visible to users who are authorized to send requests to the <b>"/security-taglib"</b> URL. 
	</sec:authorize>
	
	<h3>2) The authentication tag</h3>
	
	<h4>2.1) The csrfInput Tag</h4>
	<p>If CSRF protection is not enabled, this tag outputs nothing. We must not plate the tag within the <code>&ltform:form&gt>&lt/form:form&gt</code> block.</p>
	<pre>
		&lt;form method="post" action="/do-something"&gt; 
		   	&lt;sec:csrfInput /&gt; 
		  	Username:&lt;br /&gt; 
		  	&lt;input type="text" username="username" /&gt; 
		   	...
		&lt;/form&gt;
	</pre>
</body>

</html>

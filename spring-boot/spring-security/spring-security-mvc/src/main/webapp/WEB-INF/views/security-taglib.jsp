<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<head>
    <meta charset="utf-8">
	<link rel="shortcut icon" href="#">
    <title>Security Taglib</title>
</head>

<style>
	body {
		counter-reset: section;
	}

	h3::before {
		counter-increment: section;
		content: counter(section) ") ";
	}	
</style>

<div class="container">
	<h2 class="text-center">
		<a target="_blank" href="https://docs.spring.io/spring-security/site/docs/4.2.x/reference/html/taglibs.html">Spring Security - Taglib</a>
	</h2>
	Thêm thẻ
	<pre>
		&lt;%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%&gt;
	</pre>
	<h3>The authorize Tag</h3>
	<%--
		<sec:authorize access="hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')" var="isAuthenticated"></sec:authorize>
	    isAuthenticated: <c:out value="${isAuthenticated}"/>
		<sec:authorize access="isAuthenticated()">Logout</sec:authorize>
		<sec:authorize access="hasRole('ADMIN')">Hello Admin</sec:authorize>
	    <sec:authorize access="hasAnyRole('ROLE_ADMIN')">todo</sec:authorize>
    --%>
	<pre>
	    &lt;sec:authorize access="hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')" var="isAuthenticated"&gt;
		// todo
	    &lt;/sec:authorize&gt;
	    
	    isAuthenticated: &lt;c:out value="${isAuthenticated}"/&gt;
	    
	    &lt;sec:authorize access="isAuthenticated()"&gt;Logout&lt;/sec:authorize&gt;
	    
	    &lt;sec:authorize access="hasRole('ADMIN')"&gt;Hello Admin&lt;/sec:authorize&gt;
	    
	    &lt;sec:authorize access="hasAnyRole('ROLE_ADMIN')"&gt;todo&lt;/sec:authorize&gt;
	</pre>
	
	<ul class="list">
		<li><p><b>hasRole("ADMIN")</b>: Evaluates to true if the current user has the admin role.</p></li>
		<li><p><b>hasAnyRole("ADMIN", "USER")</b>: Evaluates to true if the current user has any of the listed roles</p></li>
		<li><p><b>isAnonymous()</b>: Evaluates to true if the current user is an anonymous user</p></li>
		<li><p><b>isRememberMe()</b>: Evaluates to true if the current user is a remember-me user</p></li>
		<li><p><b>isFullyAuthenticated()</b>: Evaluates to true if the user is authenticated and is neither anonymous nor a remember-me user</p></li>
	</ul>
	
	<h3 style="color: red">URL</h3>
	<sec:authorize url="/security-taglib">
		This content will only be visible to users who are authorized to send requests to the <b>"/security-taglib"</b> URL.
	</sec:authorize>
	
	<%-- <sec:authorize url="/security-taglib">
    	<a href="/security-taglib">Manage Users</a>
	</sec:authorize> --%>
	
	<h3>The authentication Tag</h3>
	<%-- <sec:authorize access="isAuthenticated()">
    	Welcome Back, <sec:authentication property="name"/>
	</sec:authorize> --%>
	<pre>
		&lt;sec:authorize access="isAuthenticated()"&gt;
			Welcome Back, &lt;sec:authentication property="name"/&gt;
		&lt;/sec:authorize&gt;
	</pre>
	
	hoặc
	
	<pre>
		&lt;sec:authentication property="principal.username" /&gt;
	</pre>
	
	<h3>The csrfInput Tag</h3>
	Hopefully, we have Spring Security's CSRF defense enabled in our app!
	If we do, then Spring Security already inserts a CSRF hidden form input inside <code>&lt;form:form&gt;</code> tags for us.
	But in case we want to use &lt;form&gt; instead, we can manually indicate where Spring Security should place this hidden input field using csrfInput:
	<pre>
		&lt;form method="post" action="/do-something"&gt; 
			&lt;input type="text" username="username" /&gt;
			&lt;sec:csrfInput /&gt; 
		&lt;/form&gt;
	</pre>
	<p>If CSRF protection is not enabled, this tag outputs nothing. 
</div>

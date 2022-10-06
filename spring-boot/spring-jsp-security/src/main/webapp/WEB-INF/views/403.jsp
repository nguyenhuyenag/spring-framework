<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<head>
	<title>Access Denied</title>
	<link rel="shortcut icon" href="#">
</head>

<h3>Hi <strong style="color: red;"><sec:authentication property="name"/></strong>!</h3>
<h3>You don't have permission to access this page! <img src="https://i.imgur.com/5IHep82.png"></h3>
<%-- <c:if test="${message != null}">
	<h3 style="color: red;">${message}</h3>
</c:if> --%>

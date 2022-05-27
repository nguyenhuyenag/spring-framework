<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
	<title>Access Denied</title>
	<link rel="shortcut icon" href="#">
</head>

<c:if test="${message != null}">
	<h3 style="color: red;">${message}</h3>
</c:if>

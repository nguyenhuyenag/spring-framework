<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>
<head>
	<title>Access Denied</title>
</head>
 
<body>
    <!-- Include _menu.html -->
	<%@ include file="menu.jsp" %>
	
 	<c:if test="${message != null}">
    	<h3 style="color: red;">${message}</h3>
 	</c:if>
 	
 	<c:if test="${userInfo != null}">
    	<div>${userInfo}</div>
 	</c:if>
 	
</body>
 
</html>

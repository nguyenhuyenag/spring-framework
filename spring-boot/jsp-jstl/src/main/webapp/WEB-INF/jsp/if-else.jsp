<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8" />
	<title>${title}</title>
	<link rel="stylesheet"  href="static/style.css">
</head>
<body>
	<a href="${CONTEXT_PATH}/home">Home</a>
	<h1>${title}</h1>
	<c:choose>
		<%-- Khi tham số color == 'red' --%>
		<c:when test="${param.color == 'red'}">
			<p style="color: red;">RED COLOR</p>
		</c:when>
		<%-- Khi tham số color == 'blue' --%>
		<c:when test="${param.color == 'blue'}">
			<p style="color: blue;">BLUE COLOR</p>
		</c:when>
		<%-- Các trường hợp khác --%>
		<c:otherwise>
			<b>Other color</b>
		</c:otherwise>
	</c:choose>
	<form:form action="if-else" modelAttribute="colorForm">  
        Red <form:radiobutton path="color" value="red" />
		<br>
        Blue <form:radiobutton path="color" value="blue" />
		<br>
        Other <form:radiobutton path="color" value="other" />
		<br>
		<br>
		<input type="submit" value="Submit" />
	</form:form>
</body>

</html>

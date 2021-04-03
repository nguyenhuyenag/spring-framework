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
	<c:if test="${empty colorForm.color}">
		<p>ColorForm.color is EMPTY or NULL</p>
	</c:if>
	<c:if test="${not empty colorForm.color}">
		Selected Value: <c:out value="${colorForm.color}" />
		<%-- <c:out value="${empty colorForm.color}" /> --%>
		<c:choose>
			<%-- Khi tham số color == 'red' --%>
			<c:when test="${colorForm.color == 'red'}">
				<p style="color: red;">RED COLOR</p>
			</c:when>
			<%-- Khi tham số color == 'blue' --%>
			<c:when test="${param.color == 'blue'}">
				<p style="color: blue;">BLUE COLOR</p>
			</c:when>
			<%-- Các trường hợp khác --%>
			<c:otherwise>
				<p>OTHER COLOR</p>
			</c:otherwise>
		</c:choose>
	</c:if>
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

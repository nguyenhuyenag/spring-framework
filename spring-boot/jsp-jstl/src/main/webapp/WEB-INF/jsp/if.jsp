<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>

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
	<!-- <h2>1) Điều kiện so sánh</h2> -->
	<c:set var="age" scope="session" value="20" />
	<c:if test="${age > 18}" var="booleanValue">
		<p>Eligible to vote because you're <c:out value="${age}" /> years old.</p>
		<p>Test result is <c:out value="${booleanValue}" /></p>
	</c:if>
</body>

</html>

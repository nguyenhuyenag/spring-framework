<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>

<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8" />
	<title>${title}</title>
	<link rel="stylesheet"  href="/static/style.css">
</head>
<body>
	<a href="${CONTEXT_PATH}/home">Home</a>
	<h2>${title}</h2>
	<h3>1)</h3>
	<c:url value="http://example.com/show-page.jsp" var="myURL">
		<c:param name="color" value="red" />
		<c:param name="background" value="blue" />
	</c:url>
	URL: <code><c:out value="${myURL}" /></code>
</body>

</html>

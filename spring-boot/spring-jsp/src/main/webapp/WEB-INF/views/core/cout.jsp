<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<title>${title}</title>
</head>
<body>
	<a href="${CONTEXT_PATH}/home">Home</a>
	<h1>${title}</h1>
	<h2>1) Basic</h2>
	Name: <c:out value="${name}" />
	<h2>2) Show default value</h2>
	Mail: <c:out value="${mail}" default="java@sun.com" />
	<h2>3) escapeXml</h2>
	<code>
		<c:out value="${'${1 < 2}'}"/>
	</code>
</body>

</html>

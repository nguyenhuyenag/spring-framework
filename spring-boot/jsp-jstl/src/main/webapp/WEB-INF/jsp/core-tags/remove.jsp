<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8" />
	<title>${title}</title>
</head>
<body>
	<a href="${CONTEXT_PATH}/home">Home</a>
	<h1>${title}</h1>
	<c:set var="userName" scope="session" value="Code Java" />
	<p>
		Before removing value from session: <c:out value="${userName}" />
	</p>
	<c:remove var="userName" />
	<p>
		After removing value from session: <c:out value="${userName}" />
	</p>
</body>

</html>

<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<title>${title}</title>
</head>
<body>
	<a href="${CONTEXT_PATH}/home">Home</a>
	<h1>${title}</h1>
	Hello: <c:out value="${name}" />
</body>

</html>

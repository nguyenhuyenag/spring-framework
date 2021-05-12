<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<title>${title}</title>
</head>
<body>
	<a href="${CONTEXT_PATH}/home">Home</a>
	<h2>${title}</h2>
	<c:catch var="ex">
		<% int a = 100 / 0; %>
	</c:catch>
	<%-- <c:if test="${ex != null}"> --%>
	<c:if test="${not empty ex}">
 		<p>Exception : ${ex}</p>
 		<p>Message: ${ex.message}</p>
	</c:if>
</body>

</html>

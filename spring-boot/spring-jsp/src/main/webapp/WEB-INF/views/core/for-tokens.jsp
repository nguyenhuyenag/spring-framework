<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>

<!DOCTYPE html>
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
	<c:forTokens items="One#Two#Three#Four" delims="#" var="el">
    	<c:out value="${el}"/><br/>
	</c:forTokens>
	<h3>2)</h3>
	<c:forTokens items="One,Two,Three,Four.Five.Six" delims="," var="el">
    	<c:out value="${el}"/><br/>
	</c:forTokens>
</body>

</html>

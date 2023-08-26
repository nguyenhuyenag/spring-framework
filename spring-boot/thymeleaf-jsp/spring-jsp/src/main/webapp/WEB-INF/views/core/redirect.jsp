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
	<!-- Tự động chuyển hướng đến `for-tokens` -->
	<c:redirect url="for-tokens"/>
	<%-- <c:redirect url="/core-tags/for-tokens"/> --%>
</body>
</html>

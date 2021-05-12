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
	<h1>&lt;c:import&gt; Demo</h1>
	<%-- <form action="${pageContext.request.contextPath}/core-tags/import" method="POST">
		Enter URL to import into this page: <br />
		<input type="text" name="urlToImport" size="50" />
		<input type="Submit" value="Import" />
	</form>
	<c:catch var="notAValidURL">
		<c:import var="webData" url="${param.urlToImport}" />
		<c:out value="${webData}" escapeXml="false" />
	</c:catch> --%>
	<c:import var="mydata" url="/core-tags/for-each"/>
	<c:out value="${mydata}"/>
</body>

</html>

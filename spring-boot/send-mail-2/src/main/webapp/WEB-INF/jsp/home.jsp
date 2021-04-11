<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<title>Mail</title>
	<script type="text/javascript">
		<c:set var="CONTEXT_PATH" value="${pageContext.request.requestURL}" scope="session"/>
	</script>
</head>
<body>
	<h2>1) Basic mail</h2>
	<h2>scheme: <code><c:out value="${pageContext.request.scheme}" /></code></h2>
	<h2>serverName: <c:out value="${pageContext.request.serverName}" /></h2>
	<h2>serverPort: <c:out value="${pageContext.request.serverPort}" /></h2>
	<h2>contextPath: <c:out value="${pageContext.request.contextPath}" /></h2>
	<h2>servletPath: <c:out value="${pageContext.request.servletPath}" /></h2>
	<h2>requestURI: <c:out value="${pageContext.request.requestURI}" /></h2>
	<h2>requestURL: <c:out value="${pageContext.request.requestURL}" /></h2>
	<h2>method: <c:out value="${pageContext.request.method}" /></h2>
	<h2>characterEncoding: <c:out value="${pageContext.request.characterEncoding}" /></h2>
<%-- 	<ul>
		<c:forEach items="${listUrls}" var="url">
			<li>${url}</li>
		</c:forEach>
	</ul> --%>
</body>

</html>


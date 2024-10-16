<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8" />
	<title>Java Mail</title>
	<link rel="shortcut icon" href="#">
	<script type="text/javascript">
		<c:set var="CONTEXT_PATH" value="${pageContext.request.contextPath}" scope="session"/>
	</script>
</head>

<body>
	<h2>Java mail</h2>
	<ul>
		<c:forEach items="${listUrls}" var="url">
			<li><a target="_blank" href="${CONTEXT_PATH}/${url}">${url}</a></li>
		</c:forEach>
	</ul>
	<h2>Spring mail</h2>
	<ul>
		<li><a target="_blank" href="${CONTEXT_PATH}/spring-send-html">spring-send-html</a></li>
	</ul>
</body>

</html>

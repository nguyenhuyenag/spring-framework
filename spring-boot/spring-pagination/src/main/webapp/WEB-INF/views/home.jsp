<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8" />
	<link rel="shortcut icon" href="#">
	<title>Spring Page</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
		<!-- global variable -->
		<script type="text/javascript">
			<c:set var="CONTEXT_PATH" value="${pageContext.request.contextPath}" scope="session"/>
		</script>
	</head>
<body>
	<!-- Include _menu.html -->
	<%@ include file="menu.jsp" %>
	<div class="container">
		<h1>This is Home page</h1>
	</div>
</body>

</html>

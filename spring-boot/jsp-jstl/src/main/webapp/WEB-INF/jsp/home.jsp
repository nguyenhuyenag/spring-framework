<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8" />
<title>Welcome</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<script type="text/javascript">
		<!-- Global variable -->
		<c:set var="CONTEXT_PATH" value="${pageContext.request.contextPath}" scope="session"/>
	</script>
</head>


<%-- <body>
	<h1>Welcome</h1>
	<h2>${message}</h2>
	<a href="${pageContext.request.contextPath}/person-list">Person List</a>
</body> --%>

<body>
	<!-- menu -->
	<div class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Spring Boot</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<!-- <li class="active"><a href="/">Home</a></li> -->
					<li><a href="${CONTEXT_PATH}/language">Language</a></li>
					<li><a href="${CONTEXT_PATH}/change-password">Change password</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="starter-template">
			<h1>Spring Boot Web JSP Example</h1>
			<h2>Message: ${message}</h2>
		</div>
	</div>
</body>

</html>

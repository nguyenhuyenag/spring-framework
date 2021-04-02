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
<body>
	<!-- menu -->
	<div class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="home"">Spring Boot</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<!-- <li class="active"><a href="/">Home</a></li> -->
					<li><a href="${CONTEXT_PATH}/language">Language</a></li>
				</ul>
			</div>
		</div>
	</div>
	<!-- end menu -->
	<div class="container">
		<div class="starter-template">
			<h1>Spring Boot JSP + JSTL</h1>
			<ul>
				<li><a href="${CONTEXT_PATH}/for-each">For Each</a></li>
				<li><a href="${CONTEXT_PATH}/el">Expression Language</a></li>
				<li><a href="${CONTEXT_PATH}/if">If</a></li>
				<li><a href="${CONTEXT_PATH}/if-else">If - Else</a></li>
			</ul>
		</div>
	</div>
</body>

</html>

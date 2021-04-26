<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8" />
	<link rel="shortcut icon" href="#">
	<title>Spring JSP</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
		<!-- global variable -->
		<script type="text/javascript">
			<c:set var="CONTEXT_PATH" value="${pageContext.request.contextPath}" scope="session"/>
		</script>
</head>

<body>
	<div class="navbar navbar-inverse">
		<div class="container">
<!-- 			<div class="navbar-header">
				<a class="navbar-brand" href="#">Home</a>
			</div> -->
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="${CONTEXT_PATH}/search">Search</a></li>
					<li><a href="${CONTEXT_PATH}/user/">User</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="container">
		<h1>Spring Boot AJAX</h1>
	</div>
</body>

</html>

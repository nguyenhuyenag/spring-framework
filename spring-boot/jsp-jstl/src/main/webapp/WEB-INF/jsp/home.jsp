<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8" />
	<title>Home</title>
		<link rel="stylesheet"  href="/static/style.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
		<!-- Global variable -->
		<script type="text/javascript">
			<c:set var="CONTEXT_PATH" value="${pageContext.request.contextPath}" scope="session"/>
		</script>
	</head>
<body>
	<!-- menu -->
	<div class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="home">Spring Boot</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<!-- <li class="active"><a href="/">Home</a></li> -->
					<li><a href="${CONTEXT_PATH}/core/language">Language</a></li>
				</ul>
			</div>
		</div>
	</div>
	<!-- end menu -->
	<div class="container">
		<div class="starter-template">
			<h2>Spring Boot JSP + JSTL</h2>
			<h3>HttpServletRequest</h3>
			<ul>
				<li><a href="${CONTEXT_PATH}/request">Request</a></li>
				<li><a href="${CONTEXT_PATH}/view">Create View</a></li>
				<li><a href="${CONTEXT_PATH}/view1">Create View 1</a></li>
				<li><a href="${CONTEXT_PATH}/view2">Create View 2</a></li>
				<li><a href="${CONTEXT_PATH}/view3">Create View 3</a></li>
			</ul>
			<h3>1) Core</h3>
			<ul>
				<li><a href="${CONTEXT_PATH}/core/for-each">For Each</a></li>
				<li><a href="${CONTEXT_PATH}/core/el">Expression Language</a></li>
				<li><a href="${CONTEXT_PATH}/core/if">If</a></li>
				<li><a href="${CONTEXT_PATH}/core/if-else">If - Else</a></li>
				<li><a href="${CONTEXT_PATH}/form/employee">Form submit</a></li>
				<li><a href="${CONTEXT_PATH}/core/cout">Cout</a></li>
				<li><a class="red" href="${CONTEXT_PATH}/core/set">Set</a></li>
				<li><a href="${CONTEXT_PATH}/core/remove">Remove</a></li>
				<li><a href="${CONTEXT_PATH}/core/catch">Catch</a></li>
				<li><a href="${CONTEXT_PATH}/core/for-tokens">ForTokens</a></li>
				<li><a href="${CONTEXT_PATH}/core/url">Url</a></li>
				<li><a class="red" href="${CONTEXT_PATH}/core/import">Import</a></li>
				<li><a href="${CONTEXT_PATH}/core/redirect">Redirect</a></li>
			</ul>
			<h3>2) Format</h3>
			<ul>
				<li><a class="red" href="${CONTEXT_PATH}/format/bundle">Bundle</a></li>
				<li><a href="${CONTEXT_PATH}/format/format-number">Format Number</a></li>
			</ul>
		</div>
	</div>
</body>

</html>

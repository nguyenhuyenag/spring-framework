<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="CONTEXT_PATH" value="${pageContext.request.contextPath}" scope="session"/>

<!DOCTYPE html>
<html>
<head>
	<title>Spring Boot</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div class="container mt-4">
		<h1>Spring Boot App</h1>
		<ol>
			<li>File Transfer
				<ul>
					<li><a target="_blank" href="./ftp/upload">Upload file</a></li>
					<li><a target="_blank" href="${CONTEXT_PATH}/ftp/multi-upload">Upload multiple file</a></li>
					<li><a target="_blank" href="${CONTEXT_PATH}/ftp/download">Download file</a></li>
					<li><a target="_blank" href="./ftp/download-from-url">Download from URL</a></li>
				</ul>
			</li>
		</ol>
	</div>
</body>
</html>

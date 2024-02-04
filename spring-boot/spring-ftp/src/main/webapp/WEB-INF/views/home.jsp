<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="CONTEXT_PATH" value="${pageContext.request.contextPath}" scope="session"/>

<!DOCTYPE html>
<html>
<head>
	<title>Spring Boot</title>
	<link rel="shortcut icon" href="#">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css">
</head>
<body>
	<div class="container mt-4">
		<h1>Spring Boot Download File XYZ</h1>
		<ol>
			<li>Upload File:
				<ul>
					<li><a target="_blank" href="./ftp/upload">Upload file</a></li>
					<li><a target="_blank" href="${CONTEXT_PATH}/ftp/multi-upload">Upload multiple file</a></li>
					<li><a target="_blank" href="./ftp/upload-ajax">Upload Ajax</a></li>
				</ul>
			</li>
			<li>Download:
				<ul>
					<li><a target="_blank" href="${CONTEXT_PATH}/ftp/download">Download using ByteArrayResource</a></li>
					<li><a target="_blank" href="./ftp/download-ajax">Download using Ajax</a></li>
					<li><a target="_blank" href="./ftp/download-from-url">Download from URL</a></li>
				</ul>
			</li>
		</ol>
	</div>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<title>Spring Boot</title>
	<link rel="shortcut icon" href="#">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css">
</head>
<body>
	<div class="container mt-4">
		<h1>Spring Boot Upload/Download File</h1>
		<ol>
			<li>Upload File (Using multipart):
				<ul>
					<li><a target="_blank" href="./ftp/upload">Upload file</a></li>
					<li><a target="_blank" href="${pageContext.request.contextPath}/ftp/upload-multiple-files">Upload multiple file</a></li>
				</ul>
			</li>
			<li>Upload File Using Ajax:
				<ul>
					<li><a target="_blank" href="./ftp/upload-ajax">Upload Ajax</a></li>
				</ul>
			</li>
			<li>Download:
				<ul>
					<li><a target="_blank" href="./ftp/download-ajax">Download</a></li>
					<li><a target="_blank" href="./ftp/download-from-url">Download from URL</a></li>
					<li><a target="_blank" href="./ftp/upload-auto-delete">Upload Auto Delete</a></li>
				</ul>
			</li>
		</ol>
	</div>
</body>
</html>

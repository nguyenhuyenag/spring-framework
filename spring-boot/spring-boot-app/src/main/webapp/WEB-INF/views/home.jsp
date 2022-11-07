<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="CONTEXT_PATH" value="${pageContext.request.contextPath}" scope="session"/>

<!DOCTYPE html>
<html>
<head>
	<title>Spring Boot</title>
</head>
<body>
	<h1>Spring Boot App</h1>
	<ol>
		<li>FileTransfer
			<ul>
				<li><a target="_blank" href="${CONTEXT_PATH}/ftp/download?fileid=J9VWJBPIJKQCMFY4F8UM">Download file</a></li>
				<li><a target="_blank" href="/ftp/upload">Upload file</a></li>
				<li><a target="_blank" href="${CONTEXT_PATH}/ftp/multi-upload">Upload multiple file</a></li>
			</ul>
		</li>
	</ol>
</body>
</html>

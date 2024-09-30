<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="CONTEXT_PATH" value="${pageContext.request.contextPath}" scope="session" />

<!DOCTYPE html>
<html>
<head>
	<title>Download</title>
	<link rel="shortcut icon" href="#">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<style>
		table, th, td {
  			border: 1px solid black;
  			border-collapse: collapse;
		}
	</style>
</head>
<body>
	<div class="container">
		<p><a href="javascript:window.close();">Close</a></p>
		<h1>Total file: ${fn:length(files)}</h1>
		<table class="table">
			<tr>
				<th class="text-center">STT</th>
				<th>File</th>
				<th class="text-center">Download</th>
			</tr>
			<c:forEach items="${files}" var="file" varStatus="loop">
				<tr>
					<td class="text-center">${loop.index + 1}</td>
					<td>${file.fileName}</td>
					<td class="text-center"><a href="./download-ajax?fileId=${file.fileId}">Download</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>

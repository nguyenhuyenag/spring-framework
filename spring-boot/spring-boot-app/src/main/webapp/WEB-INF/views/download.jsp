<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="CONTEXT_PATH" value="${pageContext.request.contextPath}" scope="session" />

<!DOCTYPE html>
<html>
<head>
	<title>Download</title>
	<style>
		table, th, td {
  			border: 1px solid black;
  			border-collapse: collapse;
		}
	</style>
</head>
<body>
	<h1>Total file: ${fn:length(files)}</h1>
	<table style="width: 50%">
		<tr>
			<th>STT</th>
			<th>File</th>
			<th>Download</th>
		</tr>
		<c:forEach items="${files}" var="file" varStatus="loop">
		    <tr>
				<td>${loop.index + 1}</td>
				<td>${file.fileName}</td>
				<td><a href="./download-file?fileid=${file.fileId}">Download</a></td>
		    </tr>
		</c:forEach>
	</table>
</body>
</html>

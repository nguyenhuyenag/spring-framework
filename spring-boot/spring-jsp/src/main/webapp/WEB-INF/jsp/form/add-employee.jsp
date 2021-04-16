<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE HTML>
<html>
<head>
</head>
<body>
	<a href="${CONTEXT_PATH}/employee">Back</a>
	<h2>Submitted Employee Information</h2>
	<table>
		<tr>
			<td>Name :</td>
			<td>${name}</td>
		</tr>
		<tr>
			<td>ID :</td>
			<td>${id}</td>
		</tr>
		<tr>
			<td>Contact Number :</td>
			<td>${contactNumber}</td>
		</tr>
	</table>
</body>
</html>

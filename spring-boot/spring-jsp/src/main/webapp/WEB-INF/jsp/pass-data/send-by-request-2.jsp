<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<title>Page 2</title>
	<link rel="shortcut icon" href="#">
</head>
<body>
	<a href="${CONTEXT_PATH}/pass-data/page-1">Back</a>
	<h2>Send data by request (page 2)</h2>
	<%
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		request.setAttribute("name", name);
		request.setAttribute("age", age);
	%>
	<form>
		<table>
			<tr>
				<td><b>Name:</b></td>
				<td><input type='text' name='name' value='<%=request.getAttribute("name")%>'></td>
			</tr>
			<tr>
				<td><b>Age:</b></td>
				<td><input type='text' name='age' value='<%=request.getAttribute("age")%>'></td>
			</tr>
		</table>
	</form>
</body>

</html>


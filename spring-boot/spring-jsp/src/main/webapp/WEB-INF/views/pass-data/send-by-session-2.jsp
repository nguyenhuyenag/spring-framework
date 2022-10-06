<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<title>Page 2</title>
	<link rel="shortcut icon" href="#">
</head>
<body>
	<a href="${CONTEXT_PATH}/pass-data/send-by-session-1">Back</a>
	<h2>Send data by session (page 2)</h2>
	<%
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		session.setAttribute("name", name);
		session.setAttribute("age", age);
	%>
	<form>
		<table>
			<tr>
				<td><b>Name:</b></td>
				<td><input type='text' name='name' value='<%=session.getAttribute("name")%>'></td>
			</tr>
			<tr>
				<td><b>Age:</b></td>
				<td><input type='text' name='age' value='<%=session.getAttribute("age")%>'></td>
			</tr>
		</table>
	</form>
</body>

</html>


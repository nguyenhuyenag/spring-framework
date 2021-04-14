<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<title>Page 2</title>
	<link rel="shortcut icon" href="#">
</head>
<body>
	<a href="${CONTEXT_PATH}/home">Home</a>
	<h2>Pass data from one jsp to another jsp</h2>
	<%
		String username = request.getParameter("username");
		session.setAttribute("username", username);
	%>
	<b>Data form Page 1:</b>
	<input type="text"  name="usnername" value="<%=session.getAttribute("username")%>" />
</body>

</html>


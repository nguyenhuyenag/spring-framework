<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.Date" %> <!-- equivalent to import java.uti.* in plain java -->

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<title>${title}</title>
	<link rel="shortcut icon" href="#">
</head>
<body>
	<a href="${CONTEXT_PATH}/home">Home</a>
	<h2>${title}</h2>
	<%!
		int data = 50;
		public String getName(String name) {
			return name;
		}
	%>
	<%="Welcome to JSP"%> <!-- equivalent to out.println("Welcome to JSP"); -->
	<br /><br />
	<%="Value of the variable is: " + data%>
	<br /><br />
	<%="Hello: " + getName("Java")%>
	<br /><br />
	<%="Date: " + new Date()%>
</body>

</html>


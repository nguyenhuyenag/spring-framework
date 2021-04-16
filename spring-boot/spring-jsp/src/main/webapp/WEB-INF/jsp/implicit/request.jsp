<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.Arrays" %> <!-- equivalent to import java.uti.Arrays in plain java -->
<%@ page import="java.util.Enumeration" %>

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
	<h3>1) getParameter() & getParameterNames()</h3>
	<%

		// String username = request.getParameter("username");
		// out.println("Name: " + username);
		Enumeration<String> enumeration = request.getParameterNames();
		while (enumeration.hasMoreElements()) {
			String paramName = enumeration.nextElement().toString();
			out.println(paramName + " = " + request.getParameter(paramName) + "<br>");
		}
	%>
	<h3>2) getParameterValues()</h3>
	<%
		String[] arr = request.getParameterValues("version");
		out.println(Arrays.toString(arr));
	%>
	<h3>3) setAttribute() & getAttribute()</h3>
	<%
		request.setAttribute("ide1", "Eclipse");
		request.setAttribute("ide2", "IntelliJ");
		Object obj1 = request.getAttribute("ide1"); 
		Object obj2 = request.getAttribute("ide2"); 
	%>
	<p>ide1: <%=obj1.toString()%></p>
	<p>ide2: <%=obj2.toString()%></p>
	<h3>4) removeAttribute()</h3>
</body>

</html>


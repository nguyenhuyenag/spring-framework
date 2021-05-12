<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.Arrays" %> <!-- equivalent to import java.uti.Arrays in plain java -->
<%@ page import="java.util.Enumeration" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<title>${title}</title>
	<link rel="shortcut icon" href="#">
	<link rel="stylesheet"  href="${CONTEXT_PATH}/static/style.css">
</head>
<body>
	<a href="${CONTEXT_PATH}/home">Home</a>
	<h2>${title}</h2>
	
	<h3 class="red">1) getParameter() & getParameterNames()</h3>
	<%
		Enumeration<String> enumeration = request.getParameterNames();
		while (enumeration.hasMoreElements()) {
			String paramName = enumeration.nextElement().toString();
			out.println(paramName + " = " + request.getParameter(paramName) + "<br>");
		}
	%>
	
	<h3 class="red">2) getParameterValues()</h3>
	<%
		String[] arr = request.getParameterValues("version");
		out.println(Arrays.toString(arr));
	%>
	
	<h3 class="red">3) setAttribute() & getAttribute()</h3>
	<h4>3.1) setAttribute()</h4>
	<p><code>request.setAttribute("ide1", "Eclipse")</code></p>
	<%
		request.setAttribute("ide1", "Eclipse");
		request.setAttribute("ide2", "Intellij");
		request.setAttribute("ide3", "NetBeans");
	%>
	<h4>3.2) getAttribute()</h4>
	<p>ide1: <%=request.getAttribute("ide1").toString()%></p>
	
	<h3 class="red">4) removeAttribute()</h3>
	<%
		request.removeAttribute("ide1"); 
	%>
	<p>ide1: <%=request.getAttribute("ide1")%></p>
	
	<h3 class="red">5) getRequestURI()</h3>
	<p><%=request.getRequestURI()%></p>
	
	<h3 class="red">6) getQueryString()</h3>
	<p><%=request.getQueryString()%></p>
		
</body>

</html>


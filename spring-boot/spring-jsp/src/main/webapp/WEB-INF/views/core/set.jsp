<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.HashMap"%>

<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8" />
	<title>${title}</title>
</head>
<body>
	<a href="${CONTEXT_PATH}/home">Home</a>
	<h1>${title}</h1>
	<c:set var="name" value="Java"/>
	Hello: <c:out value="${name}" />
	<h2>Target</h2>
 	<%
        Map<String, Integer> mymap = new HashMap<>();
        mymap.put("age", 29);
        // out.println(mymap.get("name"));
        pageContext.setAttribute("mymap", mymap);
    %>

    <c:set value="3" target="${mymap}" property="age" />
	My age: <%-- <c:out value="${age}" /> --%>
    <%
        out.println(mymap.get("age"));
    %>
</body>

</html>

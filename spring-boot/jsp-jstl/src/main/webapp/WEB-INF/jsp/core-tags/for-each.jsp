<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>

<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8" />
	<title>${title}</title>
	<link rel="stylesheet"  href="/static/style.css">
</head>
<body>
	<a href="${CONTEXT_PATH}/home">Home</a>
	<h2>1) Basic</h2>
	<ul>
		<c:forEach var="i" begin="1" end="3">
			<li>i = <c:out value="${i}" /></li>
		</c:forEach>
	</ul>
	<h2>2) Step</h2>
	<ul>
		<c:forEach var="i" begin="2" end="10" step="2">
			<li>Thứ <c:out value="${i}" /></li>
		</c:forEach>
	</ul>
	<h2>3) Object</h2>
	<ul>
		<c:forEach items="${languages}" var="lang">
			<li>${lang.name}</li>
		</c:forEach>
	</ul>
	<h2>4) Object + Begin</h2>
	<ul>
		<c:forEach items="${languages}" var="lang" begin="2">
			<li>${lang.name}</li>
		</c:forEach>
	</ul>
	<h2>5) Object + Begin + End</h2>
	<ul>
		<c:forEach items="${languages}" var="lang" begin="3" end="5">
			<li>${lang.name}</li>
		</c:forEach>
	</ul>
	<h2>6) varStatus</h2>
	<ul>
		<c:forEach items="${languages}" var="lang" begin="0" end="1" step="1" varStatus="status">
			<li>${lang.name}</li>
			<ul>
				<!-- Giá trị current =  ${lang.name} -->
				<li>current: ${status.current}</li>
				<li>index: ${status.index}</li>
				<li>count: ${status.count}</li>
				<li>first: ${status.first}</li>
				<li>last: ${status.last}</li>
				<li>begin: ${status.begin}</li>
				<li>end: ${status.end}</li>
				<li>step: ${status.step}</li>
			</ul>
		</c:forEach>
	</ul>
	<h2>7) From XML</h2>
	<c:import url="http://localhost:8081/static/citizens.xml" var="citizenXML" />
	<x:parse var="doc" xml="${citizenXML}" />
	<table border="1">
		<x:forEach var="citizen" select="$doc/citizens/citizen">
			<tr>
				<td><x:out select="ssn" /></td>
				<td><x:out select="firstname" /></td>
				<td><x:out select="lastname" /></td>
				<td><x:out select="role" /></td>
				<td><x:out select="salary" /></td>
			</tr>
		</x:forEach>
	</table>
</body>

</html>

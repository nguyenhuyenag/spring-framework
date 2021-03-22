<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8" />
	<title>${msg}</title>
</head>
<body>
	<h2>1) c:forEach</h2>
	<br />
	<ul>
		<c:forEach items="${languages}" var="lang">
			<li>${lang.name}</li>
		</c:forEach>
	</ul>
	<br />
	
	<h2>2) c:forEach</h2>
	<ul>
		<c:forEach var="i" begin="2" end="7">
			<li>Thứ <c:out value="${i}"/></li>  
		</c:forEach>
	</ul>
	<a href="${CONTEXT_PATH}/home">Quay lại</a>
</body>

</html>

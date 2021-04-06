<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%-- <c:if test="${not empty param.language}">
	<c:set var="language" value="${param.language}" scope="session" />
</c:if> --%>
<%-- <fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.bundles" /> --%>

<%-- <fmt:bundle basename="com.bundles">
    <fmt:message key="codejava.welcome.text"/><br/>
    <fmt:message key="codejava.description.text"/>
</fmt:bundle> --%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>fmt:bundle example</title>
</head>
<body>
	<h2>fmt:bundle example</h2>
	<h1>Demo</h1>
	<fmt:bundle basename="com.bundles">
		<fmt:message key="login.label.username" />
		<br />
		<fmt:message key="login.label.password" />
	</fmt:bundle>
</body>
</html>

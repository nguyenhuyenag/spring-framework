<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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

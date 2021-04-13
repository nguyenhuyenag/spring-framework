<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${title}</title>
</head>
<body>
	<h3>${title}</h3>
	<fmt:requestEncoding value="UTF-8" />
	<fmt:bundle basename="com.bundles.ResourceBundle">
		<fmt:message key="count.k1">
			<fmt:param value="Nguyễn" />
		</fmt:message>
		<br />
		<fmt:message key="count.k2" />
		<br />
		<fmt:message key="count.k3">
			<fmt:param value="Huyen" />
		</fmt:message>
		<br />
		<fmt:message key="count.k4" />
		<br />
	</fmt:bundle>
</body>

</html>

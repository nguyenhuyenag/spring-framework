<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<link rel="shortcut icon" href="#">
	<title>Spring JSP</title>
	<script type="text/javascript">
		<c:set var="CONTEXT_PATH" value="${pageContext.request.contextPath}" scope="session"/>
	</script>
	<script type="text/javascript">
		function send() {
			var form = document.forms["account"];
			form.action = "${pageContext.request.contextPath}/login";
			form.submit();
		}
	</script>
</head>
<body>
	<div style="border: 1px solid #ccc; padding:5px; margin-bottom: 20px;">
	   <a href="${CONTEXT_PATH}/info">User Info</a>
	     | &nbsp;
	   <a href="@{/admin}">Admin</a>
	     | &nbsp;
	   <a href="${CONTEXT_PATH}/logout">Logout</a>
	</div>
</body>
</html>
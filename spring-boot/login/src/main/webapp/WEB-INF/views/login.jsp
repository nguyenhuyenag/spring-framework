<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<link rel="shortcut icon" href="#">
	<title>Spring JSP</title>
	<script type="text/javascript">
		function send() {
			var form = document.forms["account"];
			form.action = "${pageContext.request.contextPath}/login";
			form.submit();
		}
	</script>
</head>
<body>
	<form:form modelAttribute="account" action="" method="POST">
		<fieldset style="width: 250px;">
			<legend>Login</legend>
			<p style="color: red;">${error}</p>
			<table>
				<tr>
					<td>Name:</td>
					<td><form:input type='text' path='username' value='abc' /></td>
				</tr>
				<tr>
					<td>Age:</td>
					<td><form:input type='password' path='password' value='123' /></td>
				</tr>
				<tr>
					<td><button onclick="send()">Send</button></td>
				</tr>
			</table>
		</fieldset>
	</form:form>
</body>

</html>

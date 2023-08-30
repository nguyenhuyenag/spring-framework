<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
	<title>Edit User</title>
	<link rel="shortcut icon" href="#">
</head>

<div>
	<h2>Test Edit Username</h2>
	<c:if test="${not empty param.error && not empty SPRING_SECURITY_LAST_EXCEPTION}">
		<div class="alert alert-danger text-center">
    	<p>Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/></p>
    	</div>
	</c:if>
	<form action="${CONTEXT_PATH}/edit-user" method='POST'>
		<table>
			<tr>
				<td>User:</td>
				<td><input type='text' name='username' value='user'></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='password' value='123' /></td>
			</tr>
			<tr>
				<td><input name="submit" type="submit" value="submit" /></td>
			</tr>
		</table>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	</form>
</div>

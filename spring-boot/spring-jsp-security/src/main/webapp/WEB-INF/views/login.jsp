<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
	<title>Login</title>
	<link rel="shortcut icon" href="#">
</head>

<div>
	<h1>Login</h1>
	<c:if test="${not empty param.error && not empty SPRING_SECURITY_LAST_EXCEPTION}">
		<div class="alert alert-danger text-center">
    	<p>Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/></p>
    	</div>
	</c:if>
	<form action="${CONTEXT_PATH}/j_spring_security_check" method='POST'>
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
				<td>Remember Me?</td>
				<td><input type="checkbox" name="remember" /></td>
			</tr>
			<tr>
				<td><input name="submit" type="submit" value="submit" /></td>
				<!-- <td><button name="submit" type="submit">submit</button></td> -->
			</tr>
		</table>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	</form>
	<br> Username/password:
	<ul>
		<li>user/123</li>
		<li>admin/123</li>
	</ul>
</div>


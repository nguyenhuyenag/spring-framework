<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE HTML>
<html>
<head>
	<title>Login</title>
</head>
<body>
	<!-- Include _menu.html -->
	<%@ include file="menu.jsp" %>
	<h1>Login</h1>
	<!-- /login?error=true -->
	<c:if test="${request.getParameter('error') == 'true'}">
		<div style="color: red; margin: 10px 0px;">
			Login Failed!!!<br />
			Reason:
			<c:if test="${session!= null and session.getAttribute('SPRING_SECURITY_LAST_EXCEPTION') != null}">
				<span>${session.getAttribute('SPRING_SECURITY_LAST_EXCEPTION').message} + Static summary </span>
			</c:if>
		</div>
	</c:if>
	<h3>Enter user name and password:</h3>
	<form name='f' action="${CONTEXT_PATH}/j_spring_security_check" method='POST'>
		<table>
			<tr>
				<td>User:</td>
				<td><input type='text' name='username' value=''></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='password' /></td>
			</tr>
			<tr>
				<td>Remember Me?</td>
				<td><input type="checkbox" name="remember-me" /></td>
			</tr>
			<tr>
				<td><input name="submit" type="submit" value="submit" /></td>
			</tr>
		</table>
	</form>
	<br> Username/pass:
	<ul>
		<li>dbuser1/123</li>
		<li>dbadmin1/123</li>
	</ul>

</body>

</html>

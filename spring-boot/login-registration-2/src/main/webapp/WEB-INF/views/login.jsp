<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
	.login-container {
		position: absolute;
		top: 30%;
		left: 50%;
		transform: translate(-49%, -49%);
		width: 30%;
	}
</style>

<div class="login-container">
	<h2 class="text-center mt-4">Đăng nhập</h2>
	<c:if test="${not empty param.error && not empty SPRING_SECURITY_LAST_EXCEPTION}">
	    <div class="alert alert-danger text-center" role="alert">
	    	Tên đăng nhập hoặc mật khẩu không đúng! <br/>
	    	${SPRING_SECURITY_LAST_EXCEPTION.message} <br/>
	    	<%-- <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" /> --%>
		</div>
	</c:if>
	<form class="pd-3" name='f' action="${CONTEXT_PATH}/j_spring_security_check" method='POST'>
		<div class="form-outline mb-4">
			<input type="text" class="form-control" name='username' value='' placeholder="Tên đăng nhập" required />
		</div>
		<div class="form-outline mb-4">
			<input type="password" class="form-control" name='password' value='' placeholder="Mật khẩu" required />
		</div>
		<button type="submit" class="btn btn-primary btn-block bg-color">Login</button>
	</form>
</div>

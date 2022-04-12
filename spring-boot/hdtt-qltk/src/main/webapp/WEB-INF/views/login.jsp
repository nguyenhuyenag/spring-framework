<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
		<div class="alert alert-danger text-center">
	    	<c:choose>
		        <c:when test="${SPRING_SECURITY_LAST_EXCEPTION.message == 'Disabled_Exception'}">
					Tài khoản chưa được kích hoạt!
				</c:when>
		        <c:otherwise>Tên đăng nhập hoặc mật khẩu không đúng!</c:otherwise>
	    	</c:choose>
    	</div>
	</c:if>
	<form class="pd-3" name='f' action="${CONTEXT_PATH}/j_spring_security_check" method='POST'>
		<div class="form-outline mb-4">
			<input type="text" class="form-control" name='email' value='abc@ts24.com.vn' placeholder="Tên đăng nhập" required />
		</div>
		<div class="form-outline mb-4">
			<input type="password" class="form-control" name='password' value='123456' placeholder="Mật khẩu" required />
		</div>
		<button type="submit" class="btn btn-primary btn-block bg-color">Login</button>
	</form>
</div>

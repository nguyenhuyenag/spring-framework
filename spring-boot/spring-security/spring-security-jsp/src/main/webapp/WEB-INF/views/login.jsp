<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
	<title>Login</title>
	<link rel="shortcut icon" href="#">
</head>

<body>
	<div class="container mt-5 d-flex justify-content-center align-items-center">
	<div class="d-flex flex-column justify-content-center align-items-center border w-50 py-5 shadow-lg rounded">
		<h1 class="mb-4">Đăng Nhập</h1>
		<c:if test="${not empty param.error && not empty SPRING_SECURITY_LAST_EXCEPTION}">
			<div class="alert alert-danger w-75 text-center">
				Tài khoản hoặc mật khẩu không đúng
			</div>
		</c:if>
		<form class="w-75" action="${CONTEXT_PATH}/j_spring_security_check" method="POST">
			<div class="form-group row">
				<label for="inputUsername" class="col-3 col-form-label">Tài Khoản:</label>
				<div class="col-9">
					<input type="text" name="username" class="form-control" id="inputUsername" placeholder="Tài Khoản" value="user" />
				</div>
			</div>
			<div class="form-group row">
				<label for="inputPassword" class="col-sm-3 col-form-label">Mật khẩu:</label>
				<div class="col-sm-9">
					<input type="password" name="password" class="form-control" id="inputPassword" placeholder="Mật khẩu" value="123" />
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-9 offset-sm-3">
					<div class="form-check">
						<input type="checkbox" class="form-check-input" name="rememberMe" id="rememberMe" />
						<label class="form-check-label" for="rememberMe">Remember Me</label>
					</div>
				</div>
			</div>
			<button class="btn btn-primary w-100 mt-3" type="submit" value="submit">Đăng Nhập</button>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
	</div>
</div>

</body>



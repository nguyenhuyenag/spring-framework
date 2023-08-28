<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<head>
	<title>Login</title>
	<link rel="shortcut icon" href="#">
	<script>
		$(function () {
			$("li").click(function () {
				const [username, password] = $(this).text().split("/");
				if (username && password) {
					console.log([username, password]);
					$('#inputUsername').val(username);
					$('#inputPassword').val(password);
				}
			});
		});
	</script>
</head>

<body>
	<div class="container mt-5 d-flex justify-content-center align-items-center">
		<div
			class="d-flex flex-column justify-content-center align-items-center border w-50 py-5 shadow-lg rounded">
			<h1 class="mb-4">Đăng Nhập</h1>
			<c:if test="${not empty param.error && not empty SPRING_SECURITY_LAST_EXCEPTION}">
				<div class="alert alert-danger alert-dismissible fade show" style="width: 85%;">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					<p class="text-center">
                		Your login attempt was not successful, try again.<br /> 
						Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
            		</p>
				</div>
			</c:if>
			<form class="w-75" action="${CONTEXT_PATH}/j_spring_security_check" method="POST">
				<div class="form-group row">
					<label for="inputUsername" class="col-3 col-form-label">Tài Khoản:</label>
					<div class="col-9">
						<input type="text" name="username" class="form-control" id="inputUsername"
							placeholder="Tài Khoản" />
					</div>
				</div>
				<div class="form-group row">
					<label for="inputPassword" class="col-sm-3 col-form-label">Mật khẩu:</label>
					<div class="col-sm-9">
						<input type="password" name="password" class="form-control" id="inputPassword"
							placeholder="Mật khẩu" />
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
				<div class="row">
					<ul>
						<li class='pointer'>user/123456</li>
						<li class='pointer'>admin/123456</li>
					</ul>
				</div>
				<button class="btn btn-primary w-100 mt-3" type="submit" value="submit">Đăng Nhập</button>
				<sec:csrfInput />
				<!-- <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> -->
			</form>
		</div>
	</div>
</body>

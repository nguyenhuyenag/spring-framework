<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<head>
	<title>Login Token</title>
	<link rel="shortcut icon" href="#">
	<script>
		$(function () {
			$("li").click(function () {
				const [username, password] = $(this).text().split("/");
				if (username && password) {
					// console.log([username, password]);
					$('#inputUsername').val(username);
					$('#inputPassword').val(password);
				}
			});

			let showPassword = false;
			$('#toggle-eye').click(function() {
				showPassword = !showPassword;
				
				const hideEye = $('#hide_eye');
  				const showEye = $('#show_eye');
				
				// condition ? exprIfTrue : exprIfFalse
			  	showPassword
					? (hideEye.addClass('d-none'), showEye.removeClass('d-none'))
					: (showEye.addClass('d-none'), hideEye.removeClass('d-none'));
				
				const inputPassword = $('#inputPassword');
				const currentType = inputPassword.attr("type");
    			inputPassword.attr("type", currentType === "text" ? "password" : "text");
			});
		});
	</script>
</head>

<body>
	<sec:authorize access="isAuthenticated()">
		<div class="alert alert-secondary text-center mt-3">
			<p>You are already logged in. Please click <a href="/">here</a> to return to the forum list.</p>
		</div>
	</sec:authorize>
	<sec:authorize access="!isAuthenticated()">
		<div class="container mt-5 d-flex justify-content-center align-items-center">
			<div class="d-flex flex-column justify-content-center align-items-center border w-50 py-5 shadow-lg rounded">
				<h1 class="mb-4">Login Token</h1>
				<c:if test="${not empty param.error && not empty SPRING_SECURITY_LAST_EXCEPTION}">
					<div class="alert alert-danger alert-dismissible fade show" style="width: 85%;">
						<button type="button" class="close" data-dismiss="alert">&times;</button>
						<p class="text-center">
							Your login attempt was not successful, try again.<br /> 
							Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
						</p>
					</div>
				</c:if>
				<form class="w-75" method="POST" action="${CONTEXT_PATH}/login-token">
					<div class="form-group">
						<input class="form-control" name="tokenId" id="tokenId" placeholder="Token Id" value="c0a33e23-11d3-4bed-9ae6-b42f904d6432" />
					</div>
					<div class="form-group">
						<div class="form-check">
							<input type="checkbox" class="form-check-input" name="rememberMe" id="rememberMe" />
							<label class="form-check-label" for="rememberMe">Remember Me</label>
						</div>
					</div>
					<div class="form-group text-center">
						<sec:csrfInput />
						<button class="btn btn-primary w-100 mt-3" type="submit" value="submit">Đăng Nhập</button>
						<a href="${CONTEXT_PATH}/login">Login with username/password</a>
					</div>
					<div class="text-center text-danger">
						<c:if test="${not empty message}">${message}</c:if>
					</div>
				</form>
			</div>
		</div>
	</sec:authorize>
</body>

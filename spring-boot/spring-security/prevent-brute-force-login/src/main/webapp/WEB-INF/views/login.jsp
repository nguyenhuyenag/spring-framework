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

			let showPassword = false;
			$('#toggle-eye').click(function() {
				showPassword = !showPassword;
				
				const hideEye = $('#hide_eye');
  				const showEye = $('#show_eye');
				
				  // Cú pháp: condition ? exprIfTrue : exprIfFalse
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
			<p>You are already logged in. Please click <a href="./">here</a> to return to the forum list.</p>
		</div>
	</sec:authorize>
	
	<sec:authorize access="!isAuthenticated()">
		<div class="container mt-5 d-flex justify-content-center align-items-center">
			<div class="d-flex flex-column justify-content-center align-items-center border w-50 py-5 shadow-lg rounded">
				<h1 class="mb-4">Login</h1>
				<div id="response"></div>
				<c:if test="${not empty param.error && not empty SPRING_SECURITY_LAST_EXCEPTION}">
					<div class="alert alert-danger alert-dismissible fade show" style="width: 85%;">
						<button type="button" class="close" data-dismiss="alert">&times;</button>
						<p class="text-center">
							Your login attempt was not successful, try again.<br /> 
							Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
						</p>
					</div>
				</c:if>
				
				<form id='loginForm' class="w-75">
					<div class="form-group">
						<input class="form-control" name="username" id="inputUsername" placeholder="Tài Khoản" value="" required/>
					</div>
					<div class="form-group">
						<div class="input-group">
							<input type="password" class="form-control" name="password" id="inputPassword" placeholder="Mật khẩu" value="" required/>
							<div class="input-group-append">
								<span id="toggle-eye" class="input-group-text make-pointer">
									<!--Font awesome icon-->
									<i class="fa fa-eye d-none" id="show_eye"></i>
                  					<i class="fa fa-eye-slash" id="hide_eye"></i>
								</span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="form-check">
							<input type="checkbox" class="form-check-input" name="rememberMe" id="rememberMe" />
							<label class="form-check-label" for="rememberMe">Remember Me</label>
						</div>
					</div>
					<div class="form-group">
						<ul>
							<li class='pointer'>not_found/123456</li>
							<li class='pointer'>user1/1234567</li>
							<li class='pointer'>admin/123456</li>
						</ul>
					</div>
					<sec:csrfInput />
					<button class="btn btn-primary w-100 mt-3" type="submit" value="submit">Đăng Nhập</button>
				</form>
			</div>
		</div>
		<script>
			$(document).ready(function() {
			    // Khi form được submit
			    $("#loginForm").submit(function(e) {
			        e.preventDefault(); // Ngăn chặn sự kiện mặc định của form
			
			        // Lấy giá trị của username và password từ form
			        var username = $("#inputUsername").val();
			        var password = $("#inputPassword").val();
			
			        // Gửi AJAX request đến URL `${CONTEXT_PATH}/j_spring_security_check`
			        $.ajax({
			            url: "${CONTEXT_PATH}/j_spring_security_check",
			            method: "POST",
			            data: { username: username, password: password },
			            success: function(data) {
			            	// Xử lý kết quả ở đây
			            	// console.log(data);
			            	window.location.assign("./");
			            },
			            error: function() {
			            	// Write JSON vào HttpServletResponse và hiển thị lên view
			                $("#response").html("Có lỗi xảy ra khi thực hiện đăng nhập");
			            }
			        });
			    });
			});
			</script>
	</sec:authorize>
</body>

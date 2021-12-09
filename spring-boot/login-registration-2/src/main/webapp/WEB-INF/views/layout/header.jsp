<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
	.active {
		font-weight: bold;
	}
	.bg-color {
		background-color: #009ee3 !important;
	}
	.nav-link, .navbar-brand {
		color: white;
	}
</style>

<c:if test="${pageContext.request.userPrincipal.name != null}">
	<nav id="topheader" class="navbar navbar-expand-md mb-1 bg-color">
		<a class="navbar-brand text-uppercase" href="#">VSSID</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarCollapse">
			<ul class="navbar-nav mr-auto">
				<!-- <li id="tracuu" class="nav-item"><a class="nav-link" href="./tracuu">Tra Cứu</a></li> -->
				<li id="get-user" class="nav-item"><a class="nav-link" href="./get-user">Get user</a></li>
				<!-- <li id="doimatkhau" class="nav-item"><a class="nav-link" href="./doimatkhau">Đổi mật khẩu</a></li> -->
				<li id="logout" class="nav-item"><a class="nav-link" href="./logout">Đăng xuất</a></li>
			</ul>
		</div>
	</nav>
</c:if>

<script type="text/javascript">
	$(function () {
		let menu = ["tracuu", "get-user"];
		let i, len = menu.length;
		let path = window.location.pathname.replace("/", "");
		for (i = 0; i < len; i++) {
			if (path.startsWith(menu[i])) {
				break;
			}
		}
		if (i == len) {
			i = 0;
		}
		$('#' + menu[i]).addClass('active');
	});
</script>

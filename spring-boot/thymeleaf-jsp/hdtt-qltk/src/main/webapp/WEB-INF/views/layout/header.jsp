<%@ page contentType="text/html;charset=UTF-8"%>
<!-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> -->

<style>
	.active {
		font-weight: bold;
	}
	.bg-color {
		background-color: #009ee3 !important;
	}
	.navbar-nav, .nav-link, .navbar-brand {
		color: white !important;
	}
</style>

<c:if test="${pageContext.request.userPrincipal.name != null}">
	<nav class="navbar navbar-expand-lg navbar-dark bg-color">
	  	<div class="container-fluid">
		    <a class="navbar-brand text-uppercase" href="#">HDDT</a>
		    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup">
				<span class="navbar-toggler-icon"></span>
		    </button>
		    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
		      <div class="navbar-nav ml-auto">
		        <a class="nav-link nav-item" id="quanlytaikhoan" href="./quanlytaikhoan">Quản lý tài khoản</a>
		        <a class="nav-link nav-item" href="./logout">Đăng xuất</a>
		      </div>
		    </div>
		</div>
	</nav>
</c:if>

<script type="text/javascript">
	$(function () {
		let menu = ["quanlytaikhoan"];
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
		// todo
		/* var ids = [];
		// var ids = new Set();
		var children = document.getElementById("ids").children; //get container element children.
		for (i = 0, len = children.length ; i < len; i++) {
		    // children[i].className = 'new-class'; //change child class name.
		    ids.push(children[i].id); //get child id.
			// ids.add(children[i].id);
		}
		// ids.delete("logout");
		console.log(ids); */
	});
</script>

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

<nav id="topheader" class="navbar navbar-expand-md mb-1 bg-color">
	<a class="navbar-brand text-uppercase" href="#">HDDT</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarCollapse">
		<ul class="navbar-nav mr-auto">
			<li id="invoice" class="nav-item"><a class="nav-link" href="./invoice">Tìm kiếm Hóa Đơn</a></li>
			<!-- <li id="count-unread-message" class="nav-item"><a class="nav-link" href="./count-unread-message">Đếm Hóa Đơn</a></li> -->
		</ul>
	</div>
</nav>

<script type="text/javascript">
	$(function () {
		let menu = ["invoice", "count-unread-message"];
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

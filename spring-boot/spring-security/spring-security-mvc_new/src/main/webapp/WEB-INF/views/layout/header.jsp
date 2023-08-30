<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

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
		.pointer {
			cursor: pointer;
		}
</style>

<script type="text/javascript">
	$(function () {
		activeMenu();
	});

	function activeMenu() {
		const menu = $('#menu-items li').map((index, element) =>  element.id).get();
		const path = window.location.pathname;
		// If not found i = -1
		const i = menu.findIndex(item => path.startsWith('${CONTEXT_PATH}' + '/' + item));
		if (i != -1) {
			$('#' + menu[i]).addClass('active');
		}
	}
</script>

<sec:authorize access="isAuthenticated()">
	<nav id="topheader" class="navbar navbar-expand-md mb-1 bg-color">
		<a class="navbar-brand text-uppercase" href="./home">Spring</a>
		<div class="collapse navbar-collapse" id="navbarCollapse">
			<ul class="navbar-nav mr-auto" id='menu-items'>
				<li id="user-info" class="nav-item"><a class="nav-link" href="./user-info">UserInfo</a></li>
				<li id="security-taglib" class="nav-item"><a class="nav-link" href="./security-taglib">Taglib</a></li>
				<li id="page-abcdf" class="nav-item"><a class="nav-link" href="./page-abcdf">Page 404</a></li>
				<li id="edit-user" class="nav-item"><a class="nav-link" href="./edit-user">Edit User</a></li>
				<li id="admin" class="nav-item"><a class="nav-link" href="./admin">Admin</a></li>
				<li id="ajax" class="nav-item"><a class="nav-link" href="./ajax">Ajax</a></li>
			</ul>
			<ul class="navbar-nav" style="text-align: right;">
				<li class="nav-item"><a class="nav-link" href="./user-info">
					Hello <c:out value="${pageContext.request.remoteUser}"/></a>
				</li>
				<li class="nav-item"><a class="nav-link" href="./logout">Logout</a></li>
			</ul>
		</div>
	</nav>
</sec:authorize>

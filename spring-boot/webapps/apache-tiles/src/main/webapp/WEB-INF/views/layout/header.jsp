<style>
	.active {
		font-weight: bold;
	}
</style>

<nav id="topheader" class="navbar navbar-expand-md navbar-dark bg-dark mb-1">
	<a class="navbar-brand text-uppercase" href="/home">spring boot</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarCollapse">
		<ul class="navbar-nav mr-auto">
			<li id="home" class="nav-item"><a class="nav-link" href="/home">Home</a></li>
			<li id="contact" class="nav-item"><a class="nav-link" href="/contact">Contact</a></li>
		</ul>
		<form class="form-inline mt-2 mt-md-0">
			<input class="form-control mr-sm-2" type="text" placeholder="Search">
			<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
		</form>
	</div>
</nav>

<script type="text/javascript">
	$(function () {
		let menu = ["home", "contact"];
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

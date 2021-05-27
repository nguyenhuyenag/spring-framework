<style>
	.active {
		font-weight: bold;
	}
</style>

<nav id="topheader" class="navbar navbar-expand-md navbar-dark bg-dark mb-1">
	<a class="navbar-brand text-uppercase" href="/random">English</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarCollapse">
		<ul class="navbar-nav mr-auto">
			<li id="random" class="nav-item"><a class="nav-link" href="/random">Random</a></li>
			<li id="incomplete" class="nav-item"><a class="nav-link" href="/incomplete">Incomplete </a></li>
		</ul>
	</div>
</nav>

<script type="text/javascript">
	$(function () {
		let menu = ["random", "incomplete"];
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

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
			<li id="vocabulary" class="nav-item"><a class="nav-link" href="/vocabulary">Vocabulary</a></li>
			<li id="incomplete" class="nav-item"><a class="nav-link" href="/incomplete">Incomplete</a></li>
			<li id="plural-noun" class="nav-item"><a class="nav-link" href="/plural-noun">Plural Noun</a></li>
			<!-- <li id="incomplete" class="nav-item"><a class="nav-link" href="#">Export (???)</a></li> -->
			<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle" href="#" id="dropdown03" data-toggle="dropdown">API</a>
				<div class="dropdown-menu">
				  <a class="dropdown-item" href="/xssf/import-data" target="_blank">Import Data</a>
				  <a class="dropdown-item" href="https://dictionary.cambridge.org/dictionary/" target="_blank">Cambridge</a>
				</div>
			  </li>
		</ul>
	</div>
</nav>

<script type="text/javascript">
	$(function () {
		let menu = ["random", "incomplete", "vocabulary", "plural-noun"];
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

<style>
	footer {
		height: 50px;
		display: flex;
		color: #b7bbc8;
		align-items: center;
	}
</style>

<footer class="fixed-bottom justify-content-center bg-dark">
	<div>
		&copy; <span class="year"></span> Copyright by <b>nguyenhuyenag</b>
	</div>
</footer>

<script>
	$(function () {
		$('.year').text(new Date().getFullYear());
	});
</script>

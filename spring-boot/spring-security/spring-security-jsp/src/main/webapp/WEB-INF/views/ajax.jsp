<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<head>
	<title>Ajax</title>
	<link rel="shortcut icon" href="#">
	<script type="text/javascript">
		$(function () {
			$('button').click(function (e) {
				e.preventDefault();
				$.ajax({
					type: 'POST',
					url: './api/my-ajax?name=test2023',
					contentType: "application/json;charset=utf-8",
					data: JSON.stringify({ 'data': '123456', 'input': $('#comment').val() }),
					success: function (data, status, xhr) {
						console.log(data);
						// console.log(window.location.href);
					},
					error: function (jqXhr, textStatus, errorMessage) {
						console.log(errorMessage);
					}
				});
			});
		});
	</script>
</head>

<div>
	<div class='container'>
		<h2>Ajax</h2>
		<form id=''>
			<div class="form-group">
				<label for="comment">Body:</label>
				<input class="form-control" rows="5" id="comment" value="abcdrf">
			</div>
			<button class="btn btn-primary" type="submit">Submit</button>
		</form>
	</div>
</div>

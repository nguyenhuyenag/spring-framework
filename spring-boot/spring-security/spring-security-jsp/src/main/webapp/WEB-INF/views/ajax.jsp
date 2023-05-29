<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<head>
	<title>Ajax</title>
	<link rel="shortcut icon" href="#">
	<script type="text/javascript">
		$(function() {
			$('button').click(function(e) {
    			e.preventDefault();
				console.log("xxxxxxxxxxxxxxxx");
				$.ajax({
					type: 'POST',
					// data: { myData: 'This is my data' },
					url: '/api/ajax',
					success: function (data, status, xhr) {
						
					},
					error: function (jqXhr, textStatus, errorMessage) {
						
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
				<textarea class="form-control" rows="5" id="comment"></textarea>
			</div>
			<button class="btn btn-primary" type="submit">Submit form</button>
		</form>
	</div>
</div>

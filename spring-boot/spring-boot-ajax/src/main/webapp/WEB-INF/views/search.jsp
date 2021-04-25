<!DOCTYPE html>
<html>

<head>
	<title>Spring Boot AJAX</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="shortcut icon" href="#">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>

<body>
	<div class="container" style="min-height: 500px">
		<div class="starter-template">
			<h1>Spring Boot AJAX</h1>
			<div id="feedback"></div>
			<form class="form-horizontal" id="search-form">
				<div class="form-group form-group-lg">
					<label class="col-sm-2 control-label">Username</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="username" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" id="bth-search" class="btn btn-primary btn-lg">Search</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#search-form").submit(function(event) {
				// stop submit the form, we will post it manually
				event.preventDefault();
				fire_ajax_submit();
			});
		});
		function fire_ajax_submit() {
			var search = {}
			search["username"] = $("#username").val();
			$("#btn-search").prop("disabled", true);
			$.ajax({
				type : "POST",
				contentType : "application/json",
				url : "/api/search",
				data : JSON.stringify(search),
				dataType : 'json',
				cache : false,
				timeout : 600000,
				success : function(data) {
					var json = "<h4>Ajax Response</h4><pre>" + JSON.stringify(data, null, 4) + "</pre>";
					$('#feedback').html(json);
					console.log("SUCCESS : ", data);
					$("#btn-search").prop("disabled", false);
				},
				error : function(e) {
					var json = "<h4>Ajax Response</h4><pre>" + e.responseText + "</pre>";
					$('#feedback').html(json);
					console.log("ERROR : ", e);
					$("#btn-search").prop("disabled", false);
				}
			});
		}
	</script>
</body>
</html>

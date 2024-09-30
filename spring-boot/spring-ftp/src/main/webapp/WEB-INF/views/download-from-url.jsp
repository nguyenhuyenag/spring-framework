<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<title>Download from URL</title>
	<link rel="shortcut icon" href="#">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<style>
		button {
			cursor: pointer !important;
		}
	</style>
</head>
<body>
	<div class="container">
		<p><a href="javascript:window.close();">Close</a></p>
		<h2>Download file using Ajax</h2>
		<form>
			<div class="input-group mb-3">
				<div class="input-group-prepend">
					<span class="input-group-text" id="basic-addon1">http://localhost:8080/ftp/download-from-url?fileId=</span>
				</div>
				<input type="text" id="fileId" class="form-control" value="b24569b7-9e34-4c88-88fd-8a8c694517dc">
			</div>
		</form>
		<button id="btn-download">Download</button>
	</div>
	<script type="text/javascript">
		$(function() {
			$('#btn-download').on('click', function() {
				$.ajax({
					type : "POST",
					url : "download-from-url?fileId=" + encodeURIComponent($("#fileId").val()),
					success: function (data, textStatus, request) {
						// console.log(request.getResponseHeader('Content-Disposition'));
						var filename = request.getResponseHeader('Content-Disposition')
											.split(';')[1]
											.trim()
											.split('=')[1]
											.replace(/["']/g, '');

						var blob = new Blob([data], { type: "application/octet-stream" });
						var url = window.URL.createObjectURL(blob);
						var a = document.createElement("a");
						a.href = url;
						a.style.display = "none";
						a.download = filename || "file.ext";
						document.body.appendChild(a);
						a.click();
						window.URL.revokeObjectURL(url);
					},
					error: function (error) {
						console.error("Error:", error);
					}
				});
			});
		});
	</script>
</body>
</html>

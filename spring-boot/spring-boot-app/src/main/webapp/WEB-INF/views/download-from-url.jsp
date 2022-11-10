<%@ page contentType="text/html;charset=UTF-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<%-- <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> --%>

<!DOCTYPE html>
<html>
<head>
	<title>Download from URL</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<style>
		table, th, td {
			border: 1px solid black;
			border-collapse: collapse;
		}
		button {
			cursor: pointer;
		}
	</style>
	<script type="text/javascript">
		$(function() {
			$('#btn-download').on('click', function(){
				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "download-from-url?url=" + encodeURIComponent($("#url").val()),
					success : function(data) {
						
					},
					error : function(e) {
						console.log("ERROR: ", e);
					}
				});
			});
		});
	</script>
</head>
<body>
	<div class="container">
		<p><a href="/">Back</a></p>
		<h2>Download from URL</h2>
		<form>
			<input type="text" id="url" style="width: 500px;" required value="http://localhost:8080/ftp/download-file?fileid=J9VWJBPIJKQCMFY4F8UM" />
			<br /> <br />
			<button type="submit" id="btn-download">Download</button>
		</form>
	</div>
</body>
</html>

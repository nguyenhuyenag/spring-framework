<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<head>
	<title>Ajax</title>
	<link rel="shortcut icon" href="#">
	<script type="text/javascript">
		$(function() {
			$('button').click(function(e) {
    			e.preventDefault();
				const token = $("meta[name='_csrf']").attr("content");
				const header = $("meta[name='_csrf_header']").attr("content");
				// console.log(token, header);
				$.ajax({
					type: 'POST',
					beforeSend: function(xhr) {
                    	xhr.setRequestHeader(header, token);
                    },
					url: '/api/my-ajax?name=testxxxxxxxxxxxxxxxxx',
					success: function (data, status, xhr) {
						console.log(data);
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
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
	</div>
</div>

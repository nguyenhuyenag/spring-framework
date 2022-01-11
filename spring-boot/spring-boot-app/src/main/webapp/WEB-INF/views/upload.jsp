<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
	<title>Upload</title>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.form/4.3.0/jquery.form.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>

<body>
	<p><a href="/">Back</a></p>
	<h2>Upload File</h2>
	
	<form id="formUploadFile" method="POST" action="upload" enctype="multipart/form-data">
		File: <input type="file" name="multipartFile" /> <br /> <br/>
		<input type="submit" value="Submit" />
	</form>
	
	<br /> <br />
	<div class="progress" id="progressbox" style="height: 45px; width: 50%" >
		<div class="progress-bar progress-bar-striped active" id="progressbar"
			role="progressbar" aria-valuenow="80" aria-valuemin="0"
			aria-valuemax="100" style="width: 0%">
			<div id="status" style="text-align: center; width: 100%; margin-top: 10px"></div>
		</div>
	</div>
	<p>Status: ${statusUpload}</p>
</body>

<script>
	$(document).ready(function() {
		var exist = false;
	    $('#formUploadFile').submit(function(e) {	
	            e.preventDefault();
	            $(this).ajaxSubmit({ 
	                beforeSubmit: function() {
	                	resetProgressBar();
	                },
	                uploadProgress: function (event, position, total, percentComplete){	
	                    console.log(percentComplete+"");
	        	        $("#progressbar").width(percentComplete + '%');
	        	        if (percentComplete < 100) {
	        	        	$("#status").html(percentComplete + '%');
	        	        }
	        	        if (percentComplete == 100) {
	        	        	$("#status").html("Saving...");
	        	        }
	                },
	                success:function (responseText, statusText, xhr){
	                	$("#status").html("Completed!");
	                    $("#progressbar").removeClass('progress-bar-striped active')
	
	                },
	                error: function(responseText, statusText, xhr){
	                	resetProgressBar();
	        			console.log(responseText);
	        			console.log(statusText);
	        			console.log(xhr);
	                },
	                //url:       url         // override for form's 'action' attribute 
	                type:      'POST',        // 'get' or 'post', override for form's 'method' attribute 
	                //dataType:  null        // 'xml', 'script', or 'json' (expected server response type) 
	                clearForm: true,        // clear all form fields after successful submit 
	                resetForm: true        // reset the form after successful submit 
	            });
	    });
	    
	    function resetProgressBar() {
	        $("#progressbar").width(0 + '%');
	        $("#progressbar").addClass('progress-bar-striped active')
	        $("#status").html(0 + '%');
	    }
	});
</script>
</html>

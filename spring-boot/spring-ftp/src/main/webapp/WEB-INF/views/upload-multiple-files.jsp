<%@ page contentType='text/html;charset=UTF-8' %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>--%>
<%--<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>--%>

<html>
<head>
    <title>Upload Multiple Files</title>
    <link rel="shortcut icon" href="#">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.form/4.3.0/jquery.form.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <style>
        button, input[type="submit"], input[type='file'] {
            cursor: pointer;
        }
    </style>
</head>

<body>
<div class="container">
    <p><a href="javascript:window.close();">Close</a></p>
    <h2>Upload Multiple Files</h2>
    <!-- Form upload -->
    <form id="form-upload" method="POST" enctype="multipart/form-data" action='/ftp/upload-multiple-files'>
        <!-- <input type="file" name="multipartFile" accept="image/png, image/jpeg" multiple required /> -->
        <input type="file" name="multipartFile" accept="*" multiple required/>
        <br/> <br/>
        <input type="submit" value="Submit"/>
    </form>
    <div class="mt-1 mb-1">
        <ol class="list-files"></ol>
    </div>
    <div class="progress mt-1" id="progressbox" style="height: 45px; width: 50%">
        <div class="progress-bar progress-bar-striped active" id="progressbar" style="width: 0%">
            <div id="status" style="text-align: center; width: 100%; margin-top: 10px"></div>
        </div>
    </div>
</div>
</body>

<script>
    $('#form-upload').submit(function (e) {
        e.preventDefault();
        $(this).ajaxSubmit({
            beforeSubmit: function () {
                resetProgressBar();
            },
            uploadProgress: function (event, position, total, percentComplete) {
                console.log(percentComplete + "");
                $("#progressbar").width(percentComplete + '%');
                if (percentComplete < 100) {
                    $("#status").html(percentComplete + '%');
                }
                if (percentComplete == 100) {
                    $("#status").html("Saving...");
                }
            },
            success: function (responseText, statusText, xhr) {
                $("#status").html("Completed!");
                $("#progressbar").removeClass('progress-bar-striped active')
                    .css('background-color', '#5cb85c');
            },
            error: function (responseText, statusText, xhr) {
                resetProgressBar();
                // console.log(xhr);
                // console.log(statusText);
                // console.log(responseText);
            },
            // url:       url		// override for form's 'action' attribute
            // type:      'POST',	// 'get' or 'post', override for form's 'method' attribute
            // dataType:  null       // 'xml', 'script', or 'json' (expected server response type)
            clearForm: true,        // clear all form fields after successful submit
            resetForm: true        	// reset the form after successful submit
        });
    });

    $('#form-upload').on('change', function (evt) {
        // console.log(evt.target.files);
        $('.list-files li').remove(); // Clear the existing list
        for (let i = 0; i < evt.target.files.length; i++) {
            let file = evt.target.files[i]; // Get the current file object
            $('.list-files').append('<li>' + file.name + '</li>'); // Append the file name
            // console.log(file.name); // Log the file name
        }
    });

    function resetProgressBar() {
        $("#status").html(0 + '%');
        $("#progressbar").width(0 + '%');
        $("#progressbar").addClass('progress-bar-striped active');
    }
</script>
</html>

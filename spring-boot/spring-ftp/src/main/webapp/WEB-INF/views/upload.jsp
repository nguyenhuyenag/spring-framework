<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>Upload</title>
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
        <h2>Upload File (Multipart)</h2>
        <!-- Form upload -->
        <form id="form-upload" method="POST" enctype="multipart/form-data" action='/ftp/upload'>
            <input type="file" id="multipartFile" name="multipartFileXXX"/>
            <br/> <br/>
            <input type="submit" value="Submit"/>
        </form>
        <!-- Progress bar-->
        <div id="progressbox" class="progress mt-3" style="height: 45px; width: 50%">
            <div class="progress-bar progress-bar-striped active" id="progressbar" style="width: 0%">
                <div id="status" style="text-align: center; width: 100%; margin-top: 10px"></div>
            </div>
        </div>
    </div>
</body>

<script>
    function resetBar() {
        $("#status").html(0 + '%');
        $("#progressbar").width(0 + '%')
                         .addClass('progress-bar-striped active');
    }

    $(function () {
        $('#form-upload').submit(function (e) {
            e.preventDefault();
            $(this).ajaxSubmit({
                beforeSubmit: function () {
                    resetBar();
                },
                uploadProgress: function (event, position, total, percentComplete) {
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
                    resetBar();
                },
                // url:         url		    // override for form's 'action' attribute
                // type:        'POST',     // 'get' or 'post', override for form's 'method' attribute
                // dataType:    null        // 'xml', 'script', or 'json' (expected server response type)
                clearForm: true,            // clear all form fields after successful submit
                resetForm: true        	    // reset the form after successful submit
            });
        });
    });
</script>
</html>

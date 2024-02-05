<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="CONTEXT_PATH" value="${pageContext.request.contextPath}" scope="session"/>

<!DOCTYPE html>
<html>
<head>
    <title>Download Ajax</title>
    <link rel="shortcut icon" href="#">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.form/4.3.0/jquery.form.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <style>
        a, button, input[type="submit"], input[type='file'] {
            cursor: pointer;
        }
    </style>
</head>
<body>
    <div class="container">
        <p><a href="/">Back</a></p>
        Link: <a href="https://testfiledownload.com/">https://testfiledownload.com</a>
        <p>Upload file trả về id để download. Tự động xóa file sau khi thoát page</p>
        <!-- Upload -->
        <form action="#" method="post" enctype="multipart/form-data" id="uploadForm">
            <input type="file" name="xfile" id="fileinput"/>
            <button class="btn btn-primary" type="button" onclick="uploadFile()">Upload</button>
        </form>
        <!-- Download -->
        <button id="downloadButton" hidden="true" class="btn btn-primary" type="button" onclick="deleteFile(this)">Delete</button>
        <p id="message" class="text-success"></p>
    </div>

    <script>
        function uploadFile() {
            let formData = new FormData(document.getElementById('uploadForm'));
            $.ajax({
                type: 'POST',
                url: '${pageContext.request.contextPath}/ftp/upload-xfile',
                data: formData,
                contentType: false,
                processData: false,
                success: function (response) {
                    $('#message').text('Upload successful');
                    $('#downloadButton').removeAttr('hidden');
                    $('#downloadButton').attr('data-filename', response);
                },
                error: function (error) {
                    console.log(error);
                }
            });
        }

        function deleteFile() {
            let filename = $('#downloadButton').attr('data-filename');
            $.ajax({
                type: 'POST',
                url: '${pageContext.request.contextPath}/ftp/auto-delete?filename=' + encodeURIComponent(filename),
                success: function (response) {
                    $('#message').text('Delete successful');
                },
                error: function (error) {
                    console.log(error);
                }
            });
        }

        /**
         * Auto delete
         */
        // $(window).bind('beforeunload', function () {
        //
        // });
    </script>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
        <p><a href="/">Back</a></p>
        <h2>Upload File Using Ajax</h2>
        <hr>
        <form action="#" method="post" enctype="multipart/form-data" id="uploadForm">
            <input type="file" name="file" id="file"/>
            <button class="btn btn-primary" type="button" onclick="uploadFile()">Upload</button>
        </form>
        <hr>
        <form action="#" method="post" enctype="multipart/form-data" id="uploadFormBase64">
            <input type="file" name="file" id="fileinput"/>
            <button class="btn btn-primary" type="button" onclick="uploadAjaxBase64()">Upload Base64</button>
        </form>
    </div>
<script>
    function uploadFile() {
        let formData = new FormData(document.getElementById('uploadForm'));
        $.ajax({
            type: 'POST',
            url: '${pageContext.request.contextPath}/ftp/upload-ajax',
            data: formData,
            contentType: false,
            processData: false,
            success: function (response) {
                console.log('success');
            },
            error: function (error) {
                console.log(error);
            }
        });
    }

    // function file_to_base64(file) {
    //     let reader = new FileReader();
    //     reader.readAsBinaryString(file);
    //     reader.onload = function() {
    //         // console.log(btoa(reader.result));
    //         return btoa(reader.result);
    //     };
    //     reader.onerror = function() {
    //         console.log('there are some problems');
    //     };
    // }

    function getBase64(file) {
        return new Promise((resolve, reject) => {
            const reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = () => resolve(btoa(reader.result));
            reader.onerror = error => reject(error);
        });
    }

    function uploadAjaxBase64() {
        let myFile = $('#fileinput').prop('files');
        console.log(myFile);
        getBase64(myFile[0]).then(
            data => console.log(data)
        );

        <%--$.ajax({--%>
        <%--    type: 'POST',--%>
        <%--    url: '${pageContext.request.contextPath}/ftp/upload-ajax-base64',--%>
        <%--    data: {'filename': '', 'base64': ''},--%>
        <%--    contentType: false,--%>
        <%--    processData: false,--%>
        <%--    success: function (response) {--%>
        <%--        console.log('success');--%>
        <%--    },--%>
        <%--    error: function (error) {--%>
        <%--        console.log(error);--%>
        <%--    }--%>
        <%--});--%>
    }
</script>

</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
    <title>Upload Ajax</title>
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
    <h2>Upload File Using Ajax</h2>
    <hr>
    <form id="form-upload" action='' method="post" enctype="multipart/form-data">
        <input type="file" id="file-upload" name="file-upload"/>
        <button class="btn btn-primary" type="button" onclick="uploadFile()">Upload</button>
    </form>
    <hr>
    <form id="form-upload-multiple" action='' method="post" enctype="multipart/form-data">
        <input type="file" id="files-upload" name="files-upload" multiple/>
        <button type="button" class="btn btn-primary btn-upload-multiple">Upload Multiple</button>
    </form>
    <img class="loading" style="height: 50px; display: none;"
         src="data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHhtbG5zOnhsaW5rPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5L3hsaW5rIiB2ZXJzaW9uPSIxLjEiIHN0eWxlPSItLWFuaW1hdGlvbi1zdGF0ZTogcnVubmluZzsiPgogICAgICA8c3R5bGU+CiAgICAgICAgOnJvb3QgewogICAgICAgICAgLS1hbmltYXRpb24tc3RhdGU6IHBhdXNlZDsKICAgICAgICB9CgogICAgICAgIC8qIHVzZXIgcGlja2VkIGEgdGhlbWUgd2hlcmUgdGhlICJyZWd1bGFyIiBzY2hlbWUgaXMgZGFyayAqLwogICAgICAgIC8qIHVzZXIgcGlja2VkIGEgdGhlbWUgYSBsaWdodCBzY2hlbWUgYW5kIGFsc28gZW5hYmxlZCBhIGRhcmsgc2NoZW1lICovCgogICAgICAgIC8qIGRlYWwgd2l0aCBsaWdodCBzY2hlbWUgZmlyc3QgKi8KICAgICAgICBAbWVkaWEgKHByZWZlcnMtY29sb3Itc2NoZW1lOiBsaWdodCkgewogICAgICAgICAgOnJvb3QgewogICAgICAgICAgICAtLXByaW1hcnk6ICMyMjIyMjI7CiAgICAgICAgICAgIC0tc2Vjb25kYXJ5OiAjZmZmZmZmOwogICAgICAgICAgICAtLXRlcnRpYXJ5OiAjMDk5OGM4OwogICAgICAgICAgICAtLWhpZ2hsaWdodDogI2ZmYjk2NjsKICAgICAgICAgICAgLS1zdWNjZXNzOiAjNDFjZmFkOwogICAgICAgICAgfQogICAgICAgIH0KCiAgICAgICAgLyogdGhlbiBkZWFsIHdpdGggZGFyayBzY2hlbWUgKi8KICAgICAgICBAbWVkaWEgKHByZWZlcnMtY29sb3Itc2NoZW1lOiBkYXJrKSB7CiAgICAgICAgICA6cm9vdCB7CiAgICAgICAgICAgIC0tcHJpbWFyeTogIzIyMjIyMjsKICAgICAgICAgICAgLS1zZWNvbmRhcnk6ICNmZmZmZmY7CiAgICAgICAgICAgIC0tdGVydGlhcnk6ICMwOTk4Yzg7CiAgICAgICAgICAgIC0taGlnaGxpZ2h0OiAjZmZiOTY2OwogICAgICAgICAgICAtLXN1Y2Nlc3M6ICM0MWNmYWQ7CiAgICAgICAgICB9CiAgICAgICAgfQoKICAgICAgICAvKiB0aGVzZSBzdHlsZXMgbmVlZCB0byBsaXZlIGhlcmUgYmVjYXVzZSB0aGUgU1ZHIGhhcyBhIGRpZmZlcmVudCBzY29wZSAqLwogICAgICAgIC5kb3RzIHsKICAgICAgICAgIGFuaW1hdGlvbi1uYW1lOiBsb2FkZXI7CiAgICAgICAgICBhbmltYXRpb24tdGltaW5nLWZ1bmN0aW9uOiBlYXNlLWluLW91dDsKICAgICAgICAgIGFuaW1hdGlvbi1kdXJhdGlvbjogM3M7CiAgICAgICAgICBhbmltYXRpb24taXRlcmF0aW9uLWNvdW50OiBpbmZpbml0ZTsKICAgICAgICAgIGFuaW1hdGlvbi1wbGF5LXN0YXRlOiB2YXIoLS1hbmltYXRpb24tc3RhdGUpOwogICAgICAgICAgc3Ryb2tlOiAjZmZmOwogICAgICAgICAgc3Ryb2tlLXdpZHRoOiAwLjVweDsKICAgICAgICAgIHRyYW5zZm9ybS1vcmlnaW46IGNlbnRlcjsKICAgICAgICAgIG9wYWNpdHk6IDA7CiAgICAgICAgICByOiBtYXgoMXZ3LCAxMXB4KTsKICAgICAgICAgIGN5OiA1MCU7CiAgICAgICAgICBmaWx0ZXI6IHNhdHVyYXRlKDIpIG9wYWNpdHkoMC44NSk7CiAgICAgICAgICBmaWxsOiB2YXIoLS10ZXJ0aWFyeSk7CiAgICAgICAgfQoKICAgICAgICAuZG90czpudGgtY2hpbGQoMikgewogICAgICAgICAgYW5pbWF0aW9uLWRlbGF5OiAwLjE1czsKICAgICAgICB9CgogICAgICAgIC5kb3RzOm50aC1jaGlsZCgzKSB7CiAgICAgICAgICBhbmltYXRpb24tZGVsYXk6IDAuM3M7CiAgICAgICAgfQoKICAgICAgICAuZG90czpudGgtY2hpbGQoNCkgewogICAgICAgICAgYW5pbWF0aW9uLWRlbGF5OiAwLjQ1czsKICAgICAgICB9CgogICAgICAgIC5kb3RzOm50aC1jaGlsZCg1KSB7CiAgICAgICAgICBhbmltYXRpb24tZGVsYXk6IDAuNnM7CiAgICAgICAgfQoKICAgICAgICBAa2V5ZnJhbWVzIGxvYWRlciB7CiAgICAgICAgICAwJSB7CiAgICAgICAgICAgIG9wYWNpdHk6IDA7CiAgICAgICAgICAgIHRyYW5zZm9ybTogc2NhbGUoMSk7CiAgICAgICAgICB9CiAgICAgICAgICA0NSUgewogICAgICAgICAgICBvcGFjaXR5OiAxOwogICAgICAgICAgICB0cmFuc2Zvcm06IHNjYWxlKDAuNyk7CiAgICAgICAgICB9CiAgICAgICAgICA2NSUgewogICAgICAgICAgICBvcGFjaXR5OiAxOwogICAgICAgICAgICB0cmFuc2Zvcm06IHNjYWxlKDAuNyk7CiAgICAgICAgICB9CiAgICAgICAgICAxMDAlIHsKICAgICAgICAgICAgb3BhY2l0eTogMDsKICAgICAgICAgICAgdHJhbnNmb3JtOiBzY2FsZSgxKTsKICAgICAgICAgIH0KICAgICAgICB9CiAgICAgIDwvc3R5bGU+CgogICAgICA8ZyBjbGFzcz0iY29udGFpbmVyIj4KICAgICAgICA8Y2lyY2xlIGNsYXNzPSJkb3RzIiBjeD0iMzB2dyIvPgogICAgICAgIDxjaXJjbGUgY2xhc3M9ImRvdHMiIGN4PSI0MHZ3Ii8+CiAgICAgICAgPGNpcmNsZSBjbGFzcz0iZG90cyIgY3g9IjUwdnciLz4KICAgICAgICA8Y2lyY2xlIGNsYXNzPSJkb3RzIiBjeD0iNjB2dyIvPgogICAgICAgIDxjaXJjbGUgY2xhc3M9ImRvdHMiIGN4PSI3MHZ3Ii8+CiAgICAgIDwvZz4KICAgIDwvc3ZnPg=="/>
    <p class="result"></p>
</div>
<script>
    $('.btn-upload-multiple').on('click', function () {
        const $btn = $(this);
        const $loading = $('.loading');
        const $fileInput = $('#files-upload');
        const $result = $('.result').empty().removeClass('text-success text-danger');

        // Vô hiệu hóa nút bấm khi bắt đầu xử lý
        $btn.prop('disabled', true);

        // Kiểm tra nếu chưa chọn file
        if (!$fileInput.val()) {
            $result.addClass('text-danger').text('No file selected.');
            $btn.prop('disabled', false);
            return;
        }

        // Hiển thị biểu tượng loading
        $loading.show();

        // Tạo formData từ form có id 'form-upload-multiple'
        const formData = new FormData(document.getElementById('form-upload-multiple'));

        // Gửi request AJAX
        $.ajax({
            url: `${pageContext.request.contextPath}/ftp/upload-multiple-ajax`,
            type: 'POST',
            data: formData,
            contentType: false,
            processData: false,
            success: (result, status, xhr) => {
                $result.addClass('text-success').text('Success: ').append(xhr.responseText);
            },
            error: (xhr, status, error) => {
                $result.addClass('text-danger').text('Error: ').append(xhr.responseText);
            },
            complete: () => {
                $loading.hide();
                $btn.prop('disabled', false);  // Bật lại nút khi hoàn tất
            }
        });

        // Ngăn hành vi mặc định của nút bấm
        return false;
    });

    function uploadFile() {
        let $result = $('.result').empty().removeClass('text-success text-danger');

        // Check if the file input is empty
        if (!$('#file-upload').val()) {
            $result.addClass('text-danger').text('No file selected.');
            return;
        }

        let loading = $('.loading').show();
        const formData = new FormData(document.getElementById('form-upload'));
        $.ajax({
            url: `${pageContext.request.contextPath}/ftp/upload-ajax`,
            type: 'POST',
            data: formData,
            contentType: false,
            processData: false,
            success: (result, status, xhr) => {
                $result.addClass('text-success').text('Success: ').append(xhr.responseText);
            },
            error: (xhr, status, error) => {
                $result.addClass('text-danger').text('Error: ').append(xhr.responseText);
            },
            complete: (xhr, status) => {
                loading.hide();
            }
        });
    }

    /**
     * Upload file using Base64
     */
    function getBase64(file) {
        return new Promise((resolve, reject) => {
            const reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = () => resolve(btoa(reader.result));
            reader.onerror = error => reject(error);
        });
    }

    function uploadAjaxBase64() {
        const myFile = $('#fileinput').prop('files')[0];
        // console.log(myFile);
        getBase64(myFile)
            .then(data => ({
                filename: myFile.name,
                base64: data
            }))
            .then(json => $.ajax({
                type: 'POST',
                url: '${pageContext.request.contextPath}/ftp/upload-ajax-base64',
                data: JSON.stringify(json),
                contentType: "application/json; charset=utf-8",
                success: () => console.log('success'),
                error: console.error
            }));
    }
</script>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
    <title>Download from URL</title>
    <link rel="shortcut icon" href="#">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <style>
        button, .insert-id {
            cursor: pointer !important;
        }
    </style>
</head>
<body>
    <div class="container">
        <p><a href="javascript:window.close();">Close</a></p>
        <!-- Total file -->
        <h1>Total file: ${fn:length(files)}</h1>
        <!-- Form input -->
        <div style="display: flex; flex-direction: column; align-items: center;">
            <form style="width: 800px;">
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text">File ID</span>
                    </div>
                    <input type="text" id="fileId" class="form-control" placeholder="Click to file ID">
                    <div class="input-group-append">
                        <button id="btn-download" class="btn btn-primary">Download</button>
                    </div>
                </div>
            </form>
            <p class="result"></p>
            <!-- Table -->
            <div class="myTable" style="align-self: center;">
                <table class="table" style="width: 800px;">
                    <tr class="text-center">
                        <th>STT</th>
                        <th>File ID</th>
                        <th>File name</th>
                    </tr>
                    <tbody>
                        <c:forEach items="${files}" var="file" varStatus="loop">
                            <tr>
                                <td class="text-center">${loop.index + 1}</td>
                                <td class="text-center text-success insert-id" data-toggle="tooltip" data-placement="top" title="Click to insert the file ID">${file.fileId}</td>
                                <td>${file.fileName}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        $('.insert-id').on('click', function () {
            $('#fileId').val($(this).text().trim());
        });
        $('#btn-download').on('click', function (e) {
            e.preventDefault();
            $.ajax({
                type: "POST",
                url: "download-from-url?fileId=" + encodeURIComponent($("#fileId").val()),
                success: function (data, textStatus, request) {
                    // console.log(request.getResponseHeader('Content-Disposition'));
                    var filename = request.getResponseHeader('Content-Disposition')
                        .split(';')[1]
                        .trim()
                        .split('=')[1]
                        .replace(/["']/g, '');

                    var blob = new Blob([data], {type: "application/octet-stream"});
                    var url = window.URL.createObjectURL(blob);
                    var a = document.createElement("a");
                    a.href = url;
                    a.style.display = "none";
                    a.download = filename || "file.ext";
                    document.body.appendChild(a);
                    a.click();
                    window.URL.revokeObjectURL(url);
                },
                error: function (xhr, status, error) {
                    // console.log("Error:", xhr.responseText);
                    $('.result').addClass('text-danger').text("Error: " + xhr.responseText);
                }
            });
        });
    </script>
</body>
</html>

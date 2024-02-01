<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="CONTEXT_PATH" value="${pageContext.request.contextPath}" scope="session"/>

<!DOCTYPE html>
<html>
<head>
    <title>Download Ajax</title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.form/4.3.0/jquery.form.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }

        a {
            cursor: pointer;
        }
    </style>
</head>
<body>
    <div class="container">
        <p><a href="/">Back</a></p>
        <h1>Total file: ${fn:length(files)}</h1>
        <table class="table">
            <tr>
                <th>STT</th>
                <th>File</th>
                <th style="text-align: center">Download</th>
                <th style="text-align: center">Download by Base64</th>
            </tr>
            <c:forEach items="${files}" var="file" varStatus="loop">
                <tr>
                    <td style='text-align: center'>${loop.index + 1}</td>
                    <td>${file.fileName}</td>
                    <td style="text-align: center">
                        <a onclick='downloadAjax(this)' data-fileId='${file.fileId}' href="#">Download</a>
                    </td>
                    <td style="text-align: center">
                        <a onclick='downloadAjaxBase64(this)' data-fileId='${file.fileId}' href="#">Download</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <script>
        function downloadAjax(event) {
            const fileId = $(event).attr('data-fileId');
            // console.log('fileId: ' + fileId);
            $.ajax({
                type: "POST",
                url: "./download-ajax?fileId=" + fileId,
                xhrFields: {
                    responseType: 'blob' // Set the response type to 'blob'
                },
                success: function (responseData, textStatus, jqXHR) {
                    // Create a Blob from the response data
                    let blob = new Blob([responseData], {type: 'application/octet-stream'});
                    // Get the filename from the Content-Disposition header
                    let filename = '';
                    let disposition = jqXHR.getResponseHeader('Content-Disposition');
                    if (disposition && disposition.indexOf('attachment') !== -1) {
                        let filenameRegex = /filename[^;=\n]*=((['"]).*?\2|[^;\n]*)/;
                        let matches = filenameRegex.exec(disposition);
                        if (matches != null && matches[1]) {
                            filename = matches[1].replace(/['"]/g, '');
                        }
                    }
                    // Create a download link
                    let downloadLink = document.createElement('a');
                    downloadLink.href = window.URL.createObjectURL(blob);
                    // Set the file name (use the extracted filename if available, otherwise use a default name)
                    downloadLink.download = filename || 'downloaded_file.txt';
                    // Append the link to the body
                    document.body.appendChild(downloadLink);
                    // Trigger a click event on the link to prompt the user to download the file
                    downloadLink.click();
                    // Remove the link from the body
                    document.body.removeChild(downloadLink);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log(textStatus, errorThrown);
                }
            });
        }

        function base64ToBlob(base64String) {
            const byteCharacters = atob(base64String);
            const byteNumbers = new Array(byteCharacters.length);
            for (let i = 0; i < byteCharacters.length; i++) {
                byteNumbers[i] = byteCharacters.charCodeAt(i);
            }
            const byteArray = new Uint8Array(byteNumbers);
            return new Blob([byteArray], {type: 'application/octet-stream'});
        }

        function downloadAjaxBase64(event) {
            const fileId = $(event).attr('data-fileId');
            $.ajax({
                type: "POST",
                url: "./download-ajax-base64?fileId=" + fileId,
                // contentType: 'application/json',
                // dataType: 'json', // specify the dataType as JSON
                success: function (responseData, textStatus, jqXHR) {
                    const {base64, filename} = responseData;

                    // Create blob
                    const blob = base64ToBlob(base64);

                    // Create download link
                    const downloadLink = document.createElement('a');
                    downloadLink.href = URL.createObjectURL(blob);
                    downloadLink.download = filename;
                    document.body.appendChild(downloadLink);
                    downloadLink.click();
                    document.body.removeChild(downloadLink);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log(textStatus, errorThrown);
                }
            });
        }
    </script>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Recaptcha</title>
        <meta charset="UTF-8">
        <link rel="shortcut icon" href="#">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    </head>
<body>
    <div class="container mt-4">
        <h3>Captcha:</h3>
        <ol>
            <li><a href="${pageContext.request.contextPath}/recaptcha">ReCaptcha</a></li>
            <li><a href="${pageContext.request.contextPath}/hcaptcha">HCaptcha</a></li>
            <li><a href="${pageContext.request.contextPath}/cloudflare">Cloudflare</a></li>
        </ol>
    </div>
</body>
</html>

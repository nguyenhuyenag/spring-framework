<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Recaptcha</title>
        <meta charset="UTF-8">
        <link rel="shortcut icon" href="#">
    </head>
<body>
    <h3>Captcha:</h3>
    <ol>
        <li><a href="${pageContext.request.contextPath}/recaptcha">ReCaptcha</a></li>
        <li><a href="${pageContext.request.contextPath}/hcaptcha">HCaptcha</a></li>
        <li><a href="${pageContext.request.contextPath}/cloudflare">Cloudflare</a></li>
    </ol>
</body>
</html>

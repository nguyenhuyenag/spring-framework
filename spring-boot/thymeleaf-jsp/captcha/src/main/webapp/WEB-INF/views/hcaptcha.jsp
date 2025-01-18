<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<jsp:useBean id="errorString" class="java.lang.String" scope="request"/>

<!DOCTYPE html>
<html>
<head>
    <title>HCaptcha</title>
    <meta charset="UTF-8">
    <link rel="shortcut icon" href="#">
    <!-- Cloudflare Libary -->
    <script src="https://js.hcaptcha.com/1/api.js?hl=vi" async defer></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
    <div class="container mt-4">
        <h4><a href="${pageContext.request.contextPath}/home">Back Home</a></h4>
        <h3>Login:</h3>
        <p style="color: red;">${errorString}</p>
        <form name="loginForm" method="POST" action="${pageContext.request.contextPath}/hcaptcha">
            <table>
                <tr>
                    <td>Username</td>
                    <td><input value="abc" type="text" name="username"/></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input value="123" type="password" name="password"/></td>
                </tr>
            </table>
            <!-- hCaptcha -->
            <div class="h-captcha" data-sitekey="${SITE_KEY}"></div>
            <button type="submit">Login</button>
        </form>
    </div>
</body>
</html>

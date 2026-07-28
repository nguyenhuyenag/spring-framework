<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="errorString" class="java.lang.String" scope="request"/>

<!DOCTYPE html>
<html>
<head>
    <title>ReCaptcha V3</title>
    <meta charset="UTF-8">
    <link rel="shortcut icon" href="#">

    <!-- reCAPTCHA v3 Library -->
    <script src="https://www.google.com/recaptcha/api.js?render=${SITE_KEY}"></script>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">
</head>

<body>

<div class="container mt-4">

    <h4>
        <a href="${pageContext.request.contextPath}/home">Back Home</a>
    </h4>

    <h3>Login (reCAPTCHA v3)</h3>

    <p style="color:red">${errorString}</p>

    <form id="loginForm"
          method="POST"
          action="${pageContext.request.contextPath}/recaptcha/v3">

        <table>
            <tr>
                <td>User Name</td>
                <td>
                    <input value="abc"
                           type="text"
                           name="username"/>
                </td>
            </tr>

            <tr>
                <td>Password</td>
                <td>
                    <input value="123"
                           type="password"
                           name="password"/>
                </td>
            </tr>
        </table>

        <!-- Token của reCAPTCHA v3 -->
        <input type="hidden"
               id="g-recaptcha-response"
               name="g-recaptcha-response"/>

        <input type="submit" value="Submit"/>

    </form>

</div>

<script>

    document.getElementById("loginForm").addEventListener("submit", function (e) {

        e.preventDefault();

        grecaptcha.ready(function () {

            grecaptcha.execute("${SITE_KEY}", {
                action: "login"
            }).then(function (token) {

                document.getElementById("g-recaptcha-response").value = token;

                document.getElementById("loginForm").submit();

            });

        });

    });

</script>

</body>
</html>

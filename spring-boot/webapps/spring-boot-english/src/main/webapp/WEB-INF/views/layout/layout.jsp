<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!DOCTYPE html>
<html lang="vi">

<head>
    <!-- <title>English</title> -->
    <link rel="shortcut icon" href="#">
    <title><tiles:getAsString name="title"/></title>
    <link rel="shortcut icon" href="//voz.vn/styles/next/xenforo/smilies/popopo/big_smile.png">
    <!-- <link rel="icon" type="image/x-icon" href="//https://voz.vn/styles/next/xenforo/smilies/popopo/beat_brick.png?v=01"> -->
    <!-- css -->
    <link rel="stylesheet" href="static/css/bootstrap.min.css">
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <!-- script -->
    <script src="static/script/jquery.min.js"></script>
    <script src="static/script/bootstrap.min.js"></script>
    <script src="static/script/string-utils.js"></script>
    <script>
        $(function () {
            handleRequiredMessage('');
        });
        function handleRequiredMessage(msg) {
            if (StringUtils.isEmpty(msg)) {
                msg = 'Đây là thông tin bắt buộc';
            }
            $("input[required], select[required]").attr("oninvalid", "this.setCustomValidity('" + msg + "')");
            $("input[required], select[required]").attr("oninput", "setCustomValidity('')");
        }
    </script>
    <style>
        body {
            background-color: #f8f9fa!important;;
        }
    </style>
</head>

<body>
    <div>
        <tiles:insertAttribute name="header" />
        <!-- footer: height: 50px; -->
        <div class="container" style="margin-bottom: 50px; width: 1000px; background-color:white;">
            <tiles:insertAttribute name="body" />
        </div>
        <tiles:insertAttribute name="footer" />
    </div>
</body>

</html>

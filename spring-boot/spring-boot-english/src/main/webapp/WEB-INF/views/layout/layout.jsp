<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!DOCTYPE html>
<html lang="vi">

<head>
    <!-- <meta charset="utf-8"> -->
    <!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> -->
    <title>English</title>
	<link rel="shortcut icon" href="#">
    <!-- css -->
    <link rel="stylesheet" href="static/css/bootstrap.min.css">
    <!-- script -->
    <script src="static/script/jquery.min.js"></script>
    <script src="static/script/bootstrap.min.js"></script>
    <script src="static/script/string-utils.js"></script>
    <script>
        $(function() {
            $("input[required], select[required]").attr("oninvalid", "this.setCustomValidity('Đây là thông tin bắt buộc')");
            $("input[required], select[required]").attr("oninput", "setCustomValidity('')");
        });
    </script>
</head>

<body>
    <tiles:insertAttribute name="header" />
    <div role="main" class="container">
		<tiles:insertAttribute name="body" />
    </div>
    <tiles:insertAttribute name="footer" />
</body>
</html>

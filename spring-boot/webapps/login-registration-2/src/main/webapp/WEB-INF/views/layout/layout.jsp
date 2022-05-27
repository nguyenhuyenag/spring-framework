<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="utf-8">
	<link rel="shortcut icon" href="#">
    <title>Spring Boot Security</title>
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
    <c:set var="CONTEXT_PATH" value="${pageContext.request.contextPath}" scope="session"/>
</head>

<body>
    <tiles:insertAttribute name="header" />
    <div role="main" class="container">
		<tiles:insertAttribute name="body" />
    </div>
    <%-- <tiles:insertAttribute name="footer" /> --%>
</body>
</html>

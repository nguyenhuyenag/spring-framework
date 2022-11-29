<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="utf-8">
	<link rel="shortcut icon" href="#">
    <title>HDDT</title>
    <!-- script -->
    <script src="static/script/jquery.min.js"></script>
    <script src="static/script/bootstrap.min.js"></script>
    <script src="static/script/datatables.min.js"></script>
    <script src="https://momentjs.com/downloads/moment.js"></script>
	<!-- css -->
    <link rel="stylesheet" href="static/css/bootstrap.min.css">
    <link rel="stylesheet" href="static/css/jquery.datatables.min.css">
    <!-- variable -->
    <c:set var="CONTEXT_PATH" value="${pageContext.request.contextPath}" scope="session"/>
</head>

<body>
    <tiles:insertAttribute name="header" />
    <div role="main" class="container">
		<tiles:insertAttribute name="body" />
    </div>
</body>
</html>

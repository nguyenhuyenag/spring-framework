<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="utf-8">
	<link rel="shortcut icon" href="#">
    <!-- <title>Spring</title> -->
	<!-- css -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
    <!-- <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"> -->
    <!-- script -->
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <c:set var="CONTEXT_PATH" value="${pageContext.request.contextPath}" scope="session"/>
</head>

<style>
	button {
		cursor: pointer !important;
	}
</style>

<body>
    <tiles:insertAttribute name="header" />
    <div role="main" class="container">
		<tiles:insertAttribute name="body" />
    </div>
    <%-- <tiles:insertAttribute name="footer" /> --%>
</body>
</html>

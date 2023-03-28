<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="utf-8">
	<link rel="shortcut icon" href="#">
	<!-- css -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
    <!-- script -->
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <c:set var="CONTEXT_PATH" value="${pageContext.request.contextPath}" scope="session"/>
    <style type="text/css">
    	button, input[type=submit] {
			cursor: pointer !important;
		}
    </style>
</head>

<body>
    <div class="container">
	    <tiles:insertAttribute name="header" />
		<tiles:insertAttribute name="body" />
    </div>
</body>
</html>

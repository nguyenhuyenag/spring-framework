<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!DOCTYPE html>
<html lang="vi">
	<head>
	    <meta charset="utf-8">
	    <base href="/">
		<link rel="shortcut icon" href="#">
	    <!-- script -->
	    <script src="/static/script/jquery.min.js"></script>
	    <script src="/static/script/bootstrap.min.js"></script>
	    <script src="https://momentjs.com/downloads/moment.js"></script>
		<!-- css -->
	    <link rel="stylesheet" href="static/css/bootstrap.min.css">
	    <!-- variable -->
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

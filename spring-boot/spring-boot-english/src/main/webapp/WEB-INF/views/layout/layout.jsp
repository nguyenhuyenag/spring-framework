<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="utf-8">
    <title>English</title>
	<link rel="shortcut icon" href="#">
    <link rel="stylesheet" href="static/css/bootstrap.min.css">
    <script src="static/script/jquery.min.js"></script>
    <script src="static/script/bootstrap.min.js"></script>
    <script src="static/script/common.js"></script>
</head>

<body>
    <tiles:insertAttribute name="header" />
    <div role="main" class="container">
		<tiles:insertAttribute name="body" />
    </div>
    <tiles:insertAttribute name="footer" />
</body>
</html>

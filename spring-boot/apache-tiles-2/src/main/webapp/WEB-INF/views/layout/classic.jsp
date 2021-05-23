<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="utf-8">
	<link rel="shortcut icon" href="#">
    <title>Apache Tiles</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>

<body>
    <tiles:insertAttribute name="header" />
    <div role="main" class="container bg-success">
		<tiles:insertAttribute name="body" />
    </div>
	<tiles:insertAttribute name="footer" />
</body>
</html>

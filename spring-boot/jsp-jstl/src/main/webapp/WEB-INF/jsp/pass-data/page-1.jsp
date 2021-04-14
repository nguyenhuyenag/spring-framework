<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8" />
	<title>Page 1</title>
	<link rel="shortcut icon" href="#">
</head>

<body>
	<a href="${CONTEXT_PATH}/home">Home</a>
	<h2>Pass data from one jsp to another jsp</h2>
	<form action="${CONTEXT_PATH}/pass-data/page-2">
		<input type="text" name="username">
		<input type="submit" value="send">
	</form>
</body>

</html>


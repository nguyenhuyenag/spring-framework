<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>UserInfo Page</title>
	<link rel="shortcut icon" href="#">
</head>
<body>
	<jsp:include page="menu.jsp"></jsp:include>
	<h3>UserInfo Page</h3>
	<p>Username: <b>${loginedUser.username}</b></p>
	<p>Role: <b>${loginedUser.roles}</b></p>
</body>
</html>

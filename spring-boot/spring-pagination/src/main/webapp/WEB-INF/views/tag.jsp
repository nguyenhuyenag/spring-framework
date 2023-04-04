<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Spring Data Pagination</title>
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
</head>

<body>
	<!-- Include menu.html -->
	<%@ include file="menu.jsp" %>
	<div class="container" style="margin-top: 20px;">
		<table class="table table-bordered">
			<tr>
				<th width="20px">Id</th>
				<th>Name</th>
				<th>Price</th>
				<th>Quantity</th>
				<th>Status</th>
			</tr>
			<c:forEach items='${pagedListHolder.pageList}' var="item">
				<tr>
					<td>${item.id}</td>
					<td>${item.name}</td>
					<td>${item.brand}</td>
					<td>${item.madein}</td>
					<td>${item.price}</td>
				</tr>
			</c:forEach>
		</table>
		<!-- Create url with name is pageUrl -->
		<c:url value="/tag" var="pageUrl">
			<c:param name="page" value="page_number" />
			<%-- <c:param name="size" value="~" /> --%>
		</c:url>
		<tg:paging pageList="${pagedListHolder}" pageUrl="${pageUrl}" />
	</div>
</body>
</html>
 
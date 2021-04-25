<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Spring Data Pagination</title>
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
</head>

<body>
	
	<c:url value="/product" var="pageUrl">
		<c:param name="p" value="~" />
	
	</c:url>
	
	<!-- Include _menu.html -->
	<%@ include file="../menu.jsp" %>
	
	<div class="container" style="margin-top: 20px;">
		<tg:paging pagedListHolder="${pagedListHolder}" pageUrl="${pageUrl}" />
		<table class="table table-bordered">
			<tr>
				<th width="20px">Id</th>
				<th>Name</th>
				<th>Price</th>
				<th>Quantity</th>
				<th>Status</th>
			</tr>
			<c:forEach items="${pagedListHolder.pageList}" var="item">
				<tr>
					<td>${item.id}</td>
					<td>${item.name}</td>
					<td>${item.price}</td>
					<td>${item.quantity}</td>
					<td>${item.status}</td>
				</tr>
			</c:forEach>
		</table>
		<%-- <tg:paging pagedListHolder="${pagedListHolder}" pageUrl="${pageUrl}" /> --%>
	</div>
</body>
</html>

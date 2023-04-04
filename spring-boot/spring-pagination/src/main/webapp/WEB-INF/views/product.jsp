<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Product Manager</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
	<!-- Include _menu.html -->
	<%@ include file="menu.jsp" %>
	<div class="container" style="margin-top: 20px;" align="center">
		<p><a href="${CONTEXT_PATH}/new">Create New Product</a></p>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>
						<a href="${CONTEXT_PATH}/page/${currentPage}/?sortField=id&sortDir=${reverseSortDir}">Product ID</a>
					</th>
					<th>
						<a href="${CONTEXT_PATH}/page/${currentPage}?sortField=name&sortDir=${reverseSortDir}">Name</a>
					</th>
					<th>
						<a href="${CONTEXT_PATH}/page/${currentPage}?sortField=brand&sortDir=${reverseSortDir}">Brand</a>
					</th>
					<th>
						<a href="${CONTEXT_PATH}/page/${currentPage}?sortField=madein&sortDir=${reverseSortDir}">Made In</a>
					</th>
					<th>
						<a href="${CONTEXT_PATH}/page/${currentPage}?sortField=price&sortDir=${reverseSortDir}">Price</a>
					</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${listProducts}" var="item">
					<tr>
						<td>${item.id}</td>
						<td>${item.name}</td>
						<td>${item.brand}</td>
						<td>${item.madein}</td>
						<td>${item.price}</td>
						<td>
							<a href="/edit/${product.id}">Edit</a> / 
							<a href="/delete/${product.id}">Delete</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div><i>Sorted by <b>${sortField}</b> in <b>${sortDir}</b> order</i></div>
		<c:if test="${totalPages > 1}">
			<div>
				<p>Total Items: ${totalItems}</p>
				<c:if test="${currentPage > 1}">
					<a href="/page/1?sortField=${sortField}&sortDir=${sortDir}">First</a>
				</c:if>
				<c:if test="${currentPage > 1}">
					<a href="/page/${currentPage - 1}?sortField=${sortField}&sortDir=${sortDir}">Previous</a>
				</c:if>
				<c:forEach var="i" begin="1" end="${totalPages}">
					<c:if test="${currentPage != i}">
						<a href="/page/${i}?sortField=${sortField}&sortDir=${sortDir}">${i}</a>
					</c:if>
				</c:forEach>
				<c:if test="${currentPage < totalPages}">
					<a href="/page/${currentPage + 1}?sortField=${sortField}&sortDir=${sortDir}">Next</a>
				</c:if>
				<c:if test="${currentPage < totalPages}">
					<a href="/page/${totalPages}?sortField=${sortField}&sortDir=${sortDir}">Last</a>
				</c:if>
			</div>
		</c:if>
	</div>
</body>
</html>

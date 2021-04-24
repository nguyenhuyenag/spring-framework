<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Product Manager</title>
</head>
<body>
	<!-- Include _menu.html -->
	<%@ include file="../menu.jsp" %>
	<div align="center">
		<h1>Product Manager</h1>
		<a href="${CONTEXT_PATH}/new">Create New Product</a>
		<br/><br/>
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
							<a href="/edit/${product.id}">Edit</a>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="/delete/${product.id}">Delete</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div>&nbsp;</div>
		<div><i>Sorted by <b>${sortField}</b> in <b>${sortDir}</b> order</i></div>
		<div>&nbsp;</div>
		<c:if test="${totalPages > 1}">
			<div>
				Total Items: <b>${totalItems}</b>
				
				&nbsp;&nbsp;&nbsp; - &nbsp;&nbsp;&nbsp;
				
				<c:if test="${currentPage > 1}">
					<a href="/page/1?sortField=${sortField}&sortDir=${sortDir}">First</a>
				</c:if>
<%-- 				<c:if test="${currentPage > 1}">
					<span>First</span>
				</c:if> --%>
				&nbsp;&nbsp;
				
				<c:if test="${currentPage > 1}">
					<a href="/page/${currentPage - 1}?sortField=${sortField}&sortDir=${sortDir}">Previous</a>
				</c:if>
<%-- 				<c:if test="${currentPage > 1}">
					<span>Previous</span>
				</c:if> --%>
				&nbsp;&nbsp;
				
				<c:forEach var="i" begin="1" end="${totalPages}">
					<c:if test="${currentPage != i}">
						<a href="/page/${i}?sortField=${sortField}&sortDir=${sortDir}">${i}</a>
					</c:if>
				</c:forEach>
<%-- 				<span th:each="i: ${#numbers.sequence(1, totalPages)}">
					<a th:if="${currentPage != i}" th:href="@{'/page/' + ${i} + '?sortField=' + ${sortField} + '&sortDir=' + ${sortDir}}">[[${i}]]</a>
					<span th:unless="${currentPage != i}">[[${i}]]</span>
					
				</span> --%>
				&nbsp;
				<c:if test="${currentPage < totalPages}">
					<a href="/page/${currentPage + 1}?sortField=${sortField}&sortDir=${sortDir}">Next</a>
				</c:if>
		<%-- 		<span th:unless="${currentPage < totalPages}">Next</span>
				 --%>
				&nbsp;&nbsp;
				<c:if test="${currentPage < totalPages}">
					<a href="/page/${totalPages}?sortField=${sortField}&sortDir=${sortDir}">Last</a>
				</c:if>
				<%-- <span th:unless="${currentPage < totalPages}">Last</span> --%>
			</div>
		</c:if>
	</div>
</body>
</html>

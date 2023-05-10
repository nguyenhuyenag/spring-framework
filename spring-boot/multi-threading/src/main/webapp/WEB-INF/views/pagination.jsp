<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Spring Data Pagination</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
	<!-- 
		SimplePagination: https://flaviusmatis.github.io/simplePagination.js/ 
	-->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/simplePagination.js/1.4/simplePagination.min.css"/>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/simplePagination.js/1.6/jquery.simplePagination.js"></script>
	<style>
		ul {
			list-style-type: none;
		}
	</style>
	<script type="text/javascript">
		$(function() {
		    const totalItems = 200;
		    const itemsOnPage = 10;	
		    $('.pagination-container .pagination').pagination({
		      items: totalItems,
		      itemsOnPage: itemsOnPage,
		      // cssStyle: "compact-theme",
		      onPageClick: function(pageNumber, event) {
		        console.log('Page clicked: ' + pageNumber);
		        // window.location.href = `?page=${currentPage + 1}`;
		        const currentUrl = window.location;
		        console.log(currentUrl.href);
				const urlWithoutParams = currentUrl.origin + currentUrl.pathname;
				console.log(urlWithoutParams); // retrieves the current URL without query parameters
				
				// const paramValue = $.url().param('paramName');
				// console.log(paramValue);
				
				// show all params
				const params = new URLSearchParams(window.location.search);
				params.forEach(function(value, name) {
				  console.log(name + ': ' + value); // retrieves both name and value of each parameter
				});
		      }
		    });
		});
	</script>
</head>

<body>
	<!-- Include menu.html -->
	<%@ include file="menu.jsp"%>
	<div class="container" style="margin-top: 20px;">
		<table class="table table-bordered">
			<tr>
				<th width="20px">Id</th>
				<th>Name</th>
				<th>Price</th>
				<th>Quantity</th>
				<th>Status</th>
			</tr>
			<c:forEach items='${pageList}' var="item">
				<tr>
					<td>${item.id}</td>
					<td>${item.name}</td>
					<td>${item.brand}</td>
					<td>${item.madein}</td>
					<td>${item.price}</td>
				</tr>
			</c:forEach>
		</table>
		<div class="pagination-container">
		  <ul class="pagination">
		    <!-- List of items will be populated dynamically -->
		  </ul>
		</div>
	</div>
</body>
</html>

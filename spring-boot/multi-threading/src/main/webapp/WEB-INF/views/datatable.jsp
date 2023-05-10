<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Bootrap Table</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
	
	<!-- Include Bootstrap and Bootstrap Table CSS and JS files -->
	<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script> -->
	<!-- <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script> -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap-table@1.18.3/dist/bootstrap-table.min.js"></script>

	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-table@1.18.3/dist/bootstrap-table.min.css">
	
	<!-- Define a script to load the data via an AJAX request -->
		<script>
		  $(function() {
		    $.ajax({
		      url: '/api/page-info?showContent=true', // Replace with the URL to your JSON data
		      dataType: 'json',
		      success: function(data) {
		    	console.log(data);
		        $('#myTable').bootstrapTable({
		          	data: data, // Pass the JSON data to the Bootstrap Table plugin
		          	// cache: false,
	                // height: 400,
	                // striped: true,
	                // pagination: true,
    				// paginationPages: 3,
    				// pageList: [10, 25, 50, 100],
	                // pageSize: 5, //specify 5 here
	                // pageList: [5, 10, 25, 50, 100, 200]
		        });
		      },
		      error: function(xhr, textStatus, errorThrown) {
		        console.error('Error loading JSON data: ' + textStatus + ', ' + errorThrown);
		      }
		    });
		  });
		</script>
</head>

<body>
	<!-- Include menu.html -->
	<%@ include file="menu.jsp"%>
	<div class="container" style="margin-top: 20px;">
		<table id="myTable">
		  <thead>
		    <tr>
		      <th data-field="id">ID</th>
		      <th data-field="name">Name</th>
		      <th data-field="brand">Brand</th>
		      <th data-field="price">Price</th>
		      <th data-field="madein">Madein</th>
		    </tr>
		  </thead>
		</table>
	</div>
</body>
</html>
